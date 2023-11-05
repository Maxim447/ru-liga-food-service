package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.entity.Order;
import ru.liga.deliveryservice.repository.OrderRepository;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;


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

    /**
     * Маппер для преобразования сущности Order в DeliveryDTO
     */
    private final DeliveryMapper deliveryMapper;

    /**
     * Получить все доставки по статусу
     */
    public GetResponseDTO<DeliveryDTO> getDeliveriesByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAllByStatus(status, pageRequest);
        return new GetResponseDTO<>(deliveryMapper.toDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    /**
     * Обновить статус заказа
     */
    public ResponseEntity<?> updateOrderStatus(Long id, ActionDTO actionDTO) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(actionDTO.getOrderAction());
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }
}
