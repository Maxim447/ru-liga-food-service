package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
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

    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    public ResponseEntity<?> acceptOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.KITCHEN_PREPARING);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> declineOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.KITCHEN_DECLINED);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> readyOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.KITCHEN_FINISHED);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }
}
