package ru.liga.notificationservice.rabbitMQ.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(String queueName, String payLoad) {
        rabbitTemplate.convertAndSend(queueName, payLoad);
    }

}