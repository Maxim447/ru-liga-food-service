package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервис для отправки сообщений RabbitMQ
 */
@Service
@RequiredArgsConstructor
public class RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Отправить сообщение
     */
    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);
    }
}
