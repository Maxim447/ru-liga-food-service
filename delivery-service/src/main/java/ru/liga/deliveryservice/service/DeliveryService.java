package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.enums.OrderStatus;
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
    public GetResponseDTO<DeliveryDTO> getDeliveriesByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAllByStatus(status, pageRequest);
        return new GetResponseDTO<>(DeliveryMapper.mapToDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}
