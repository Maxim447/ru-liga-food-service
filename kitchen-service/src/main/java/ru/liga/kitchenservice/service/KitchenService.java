package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.kitchenservice.client.OrderClient;
import ru.liga.kitchenservice.repository.OrderRepository;

import java.util.UUID;

/**
 * Сервис для приема заказов на кухне
 */
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
@Transactional
public class KitchenService {

    private final OrderClient orderClient;

    public void acceptOrder(UUID id) {
        orderClient.acceptOrder(id);
    }

    public void declineOrder(UUID id) {
        orderClient.denyOrder(id);
    }

    public void readyOrder(UUID id) {
        orderClient.completeOrder(id);
    }
}
