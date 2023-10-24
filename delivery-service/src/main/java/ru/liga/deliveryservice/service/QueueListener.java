package ru.liga.deliveryservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.deliveryservice.repository.CourierRepository;
import ru.liga.deliveryservice.repository.OrderRepository;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.entity.enums.CourierStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueListener {

    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;

    @RabbitListener(queues = {"courier1", "courier2"})
    public void findCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long orderId = objectMapper.readValue(message, Long.class);
        List<Courier> couriers = courierRepository.findAllByStatus(CourierStatus.FREE);
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (!couriers.isEmpty()) {
            Courier courier = couriers.get(0);
            courier.setStatus(CourierStatus.ACTIVE);
            order.setCourierId(courier);
            System.out.println("couriers found, assigning a courier to an order by id " + orderId);
        } else {
            System.out.println("no couriers found");
        }
        orderRepository.save(order);
    }

}
