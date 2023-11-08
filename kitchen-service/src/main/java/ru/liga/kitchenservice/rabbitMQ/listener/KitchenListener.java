package ru.liga.kitchenservice.rabbitMQ.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.kitchenservice.repository.OrderRepository;
import ru.liga.kitchenservice.service.KitchenService;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KitchenListener {

    private final ObjectMapper objectMapper;

    private final OrderRepository orderRepository;

    private final KitchenService kitchenService;

    @RabbitListener(queues = "Kitchen-queue")
    public void receiveOrder(@Payload String orderJson) {
        UUID orderId;
        try {
            orderId = objectMapper.readValue(orderJson, UUID.class);
            Order order = orderRepository.findById(orderId).orElseThrow();
            System.out.println("Пришёл заказ на " + order.getItems() + " с ID " + order.getId());

            double distanceBetweenCustomerAndRestaurant = calculateDistanceBetweenCustomerAndRestaurant(order.getId());
            if (distanceBetweenCustomerAndRestaurant > 10) {
                kitchenService.declineOrder(order.getId());
            }
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }
    }

    private double calculateDistanceBetweenCustomerAndRestaurant(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Restaurant restaurant = order.getRestaurantId();
        Customer customer = order.getCustomerId();
        return calculateDistanceBetweenTwoPoints(customer.getLongitude(), customer.getLatitude(), restaurant.getLongitude(), restaurant.getLatitude());
    }

    private double calculateDistanceBetweenTwoPoints(BigDecimal customerLongitude, BigDecimal customerLatitude, BigDecimal restaurantLongitude, BigDecimal restaurantLatitude) {
        double earthRadius = 6371;

        double differenceLatitude = Math.toRadians(restaurantLatitude.doubleValue() - customerLatitude.doubleValue());
        double differenceLongitude = Math.toRadians(restaurantLongitude.doubleValue() - customerLongitude.doubleValue());

        double squareHalfDistance = Math.sin(differenceLatitude / 2) *
                Math.sin(differenceLatitude / 2) +
                Math.cos(Math.toRadians(customerLatitude.doubleValue())) *
                        Math.cos(Math.toRadians(restaurantLatitude.doubleValue())) *
                        Math.sin(differenceLongitude / 2) *
                        Math.sin(differenceLongitude / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(squareHalfDistance), Math.sqrt(1 - squareHalfDistance));
        return centralAngle * earthRadius;
    }
}