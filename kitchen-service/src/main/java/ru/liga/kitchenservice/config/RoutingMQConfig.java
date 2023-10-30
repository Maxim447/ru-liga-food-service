package ru.liga.kitchenservice.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация очередей для RabbitMQ
 */
@Configuration
public class RoutingMQConfig {

    @Bean
    public Declarables myQueue() {
        Queue queueDirectFirst = new Queue("courier1", false);
        Queue queueDirectSecond = new Queue("courier2", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, queueDirectSecond, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("courier.active"),
                BindingBuilder.bind(queueDirectSecond).to(directExchange).with("courier.free"));
    }
}