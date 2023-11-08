package ru.liga.notificationservice.rabbitMQ.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Notification-queue")
    public void receiveNotification(@Payload String orderJson, @Header("message-type") String messageType) {
        UUID orderId = null;
        try {
            orderId = objectMapper.readValue(orderJson, UUID.class);
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }

        log.info("Пришёл заказ: {} с мессейдж тайп {}", orderId, messageType);

        if (messageType.equals("message-for-kitchen")) {
            rabbitTemplate.convertAndSend("Kitchen-queue", orderJson);
            log.info("Отправлено в Kitchen-queue");
        } else if (messageType.equals("message-for-customer")) {
            rabbitTemplate.convertAndSend("Order-queue", orderJson);
            log.info("Отправлено в Order-queue");
        }

    }
}