package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.Order;
import ru.liga.deliveryservice.mapper.DeliveryMapper;
import ru.liga.deliveryservice.repository.OrderRepository;

import java.util.List;


@Schema(description = "Сервис для отправки заказов курьерам")
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final OrderRepository orderRepository;

    @Operation(summary = "Получить все доставки")
    public GetDeliveriesResponseDTO getDeliveriesByStatus(String status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.getAllByStatus(status, pageRequest);
        return new GetDeliveriesResponseDTO(DeliveryMapper.mapToDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Operation(summary = "Создать доставку")
    public ResponseEntity<?> setDeliveryAction(Long id, ActionDTO actionDTO) {
        Order order = orderRepository.getOrderById(id).orElseThrow();
        switch (actionDTO.getOrderAction()) {
            case "active":
                order.setStatus(DeliveryStatus.active.toString());
                orderRepository.save(order);
                return new ResponseEntity<>(HttpStatus.OK);
            case "complete":
                order.setStatus(DeliveryStatus.complete.toString());
                orderRepository.save(order);
                return new ResponseEntity<>(HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
