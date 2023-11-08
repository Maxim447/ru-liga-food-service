package ru.liga.orderservice.rabbitMQ.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.liga.entity.Order;


import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void paymentOrder(Order order) {
        String orderJson;
        try {
            orderJson = objectMapper.writeValueAsString(order.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-kitchen");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void denyOrder(Order order) {
        String orderJson;
        try {
            orderJson = objectMapper.writeValueAsString(order.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void acceptOrder(Order order) {
        String orderJson;
        try {
            orderJson = objectMapper.writeValueAsString(order.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void completeOrder(Order order) {
        String orderJson;
        try {
            orderJson = objectMapper.writeValueAsString(order.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }
    public void sendMessageForDelivery(String message, UUID orderId) {
        String queueName = "courier1";
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("Отправлен запрос на доставку для курьеров, ждём ответ");
    }

}
