package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.entity.Order;
import ru.liga.deliveryservice.repository.OrderRepository;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;


@Schema(description = "Сервис для отправки заказов курьерам")
@RequiredArgsConstructor
@Service
@ComponentScan("ru.liga.mapper")
public class DeliveryService {

    private final OrderRepository orderRepository;
    private final AbstractMapper<Order, DeliveryDTO> deliveryMapper;
    @Operation(summary = "Получить все доставки")
    public GetResponseDTO<DeliveryDTO> getDeliveriesByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAllByStatus(status, pageRequest);
        return new GetResponseDTO<>(deliveryMapper.toDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    public ResponseEntity<?> updateOrderStatus(Long id, ActionDTO actionDTO) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(actionDTO.getOrderAction());
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }
}
