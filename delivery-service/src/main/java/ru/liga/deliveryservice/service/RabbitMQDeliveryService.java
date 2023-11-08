package ru.liga.deliveryservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RabbitMQDeliveryService{

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(String message, Long id) {
        rabbitTemplate.convertAndSend("Delivery-queue-response", message, id);
    }
}