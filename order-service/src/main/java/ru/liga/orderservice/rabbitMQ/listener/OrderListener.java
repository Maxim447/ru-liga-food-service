package ru.liga.orderservice.rabbitMQ.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.orderservice.repository.OrderRepository;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderListener {

    private final ObjectMapper objectMapper;

    private final OrderRepository orderRepository;

    @RabbitListener(queues = "Order-queue")
    public void receiveCustomer(@Payload String orderJson) {
        UUID orderId;
        try {
            orderId = objectMapper.readValue(orderJson, UUID.class);
            Order order = orderRepository.getOrderById(orderId).orElseThrow();
            if (order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
                System.out.println("Ваш заказ с ID " + orderId + " был отклонён рестораном");
            } else if (order.getStatus() == OrderStatus.KITCHEN_PREPARING) {
                System.out.println("Ваш заказ с ID " + orderId + " начали готовить");
            } else if (order.getStatus() == OrderStatus.KITCHEN_FINISHED) {
                System.out.println("Ваш заказ с ID " + orderId + " готов, ищем курьера");
            }
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }
    }
}