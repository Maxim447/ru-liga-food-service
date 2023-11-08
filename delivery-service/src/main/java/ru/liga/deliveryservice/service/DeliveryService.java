package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.deliveryservice.repository.CourierRepository;
import ru.liga.dto.*;
import ru.liga.entity.Courier;
import ru.liga.entity.enums.CourierStatus;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.entity.Order;
import ru.liga.deliveryservice.repository.OrderRepository;
import ru.liga.mapper.DeliveryMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


/**
 * Сервис для отправки заказов курьерам
 */
@RequiredArgsConstructor
@Service
@ComponentScan("ru.liga.mapper")
public class DeliveryService {

    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    private final CourierRepository courierRepository;

    /**
     * Маппер для преобразования сущности Order в DeliveryDTO
     */
    private final DeliveryMapper deliveryMapper;

    /**
     * Получить все доставки по статусу
     */
    public GetResponseDTO<DeliveryDTO> getDeliveries(PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAllByStatus(OrderStatus.KITCHEN_PREPARING, pageRequest);
        return new GetResponseDTO<>(deliveryMapper.toDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    /**
     * Обновить статус заказа на DELIVERY_DELIVERING
     */
    public void takeOrder(Long courierId, UUID id) {
        Order order = orderRepository.findById(id).orElseThrow();
        Courier courier = courierRepository.findById(courierId).orElseThrow();
        courier.setStatus(CourierStatus.ACTIVE);
        courierRepository.save(courier);
        order.setCourierId(courierRepository.findById(courierId).orElseThrow());
        order.setStatus(OrderStatus.DELIVERY_DELIVERING);
        orderRepository.save(order);
        System.out.println("Заказ принят курьером " + courierId);
    }

    /**
     * Обновить статус заказа на DELIVERY_COMPLETE
     */
    public ResponseEntity<?> complete(UUID id) {
        Order order;
        try {
            order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

            order.setStatus(OrderStatus.DELIVERY_COMPLETE);
            orderRepository.save(order);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
