package ru.liga.deliveryservice.rabbitMQ.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.deliveryservice.repository.CourierRepository;
import ru.liga.deliveryservice.repository.OrderRepository;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.entity.enums.CourierStatus;
import ru.liga.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Listener RabbitMQ
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class QueueListener {

    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    /**
     * Репозиторий для работы с базой днаыых couriers
     */
    private final CourierRepository courierRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Найти свободного курьера
     */
    @RabbitListener(queues = {"courier1"})
    public void findCouriers(String message) throws JsonProcessingException {
        UUID orderId = objectMapper.readValue(message, UUID.class);
        List<Courier> couriers = courierRepository.findAllByStatus(CourierStatus.FREE);
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (!couriers.isEmpty()) {
            Courier courier = findNearestCourier(couriers, order);
            System.out.println("Пришёл заказ № " + order.getId() + " для курьера № " + courier.getId() + ". Принять или отклонить заказ?");
        } else {
            log.warn("Свободных курьеров нет");
        }
    }

    /**
     * Поиск ближайшего курьера из списка свободных
     */
    private Courier findNearestCourier(List<Courier> couriers, Order order) {
        Restaurant restaurant = order.getRestaurantId();
        Courier nearestCourier = null;
        double minimumDistance = Double.MAX_VALUE;
        for (Courier courier : couriers) {
            double currentDistance = calculateDistanceBetweenTwoPoints(courier.getLongitude(), courier.getLatitude(), restaurant.getLongitude(), restaurant.getLatitude());
            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearestCourier = courier;
            }
        }
        return nearestCourier;
    }

    /**
     * Расчет расстояния от курьера до ресторана
     */
    private double calculateDistanceBetweenTwoPoints(BigDecimal courierLongitude, BigDecimal courierLatitude, BigDecimal restaurantLongitude, BigDecimal restaurantLatitude) {
        double earthRadius = 6371;

        double differenceLatitude = Math.toRadians(restaurantLatitude.doubleValue() - courierLatitude.doubleValue());
        double differenceLongitude = Math.toRadians(restaurantLongitude.doubleValue() - courierLongitude.doubleValue());

        double squareHalfDistance = Math.sin(differenceLatitude / 2) *
                Math.sin(differenceLatitude / 2) +
                Math.cos(Math.toRadians(courierLatitude.doubleValue())) *
                        Math.cos(Math.toRadians(restaurantLatitude.doubleValue())) *
                        Math.sin(differenceLongitude / 2) *
                        Math.sin(differenceLongitude / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(squareHalfDistance), Math.sqrt(1 - squareHalfDistance));
        return centralAngle * earthRadius;
    }
}
