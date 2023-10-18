package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.kitchenservice.dto.GetOrdersResponseDTO;
import ru.liga.kitchenservice.entity.Order;
import ru.liga.kitchenservice.mapper.OrderMapper;
import ru.liga.kitchenservice.repository.OrderRepository;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@RequiredArgsConstructor
@Service
public class KitchenService {

    private final OrderRepository orderRepository;

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders(String status) {
        List<Order> orders = orderRepository.getOrdersByStatus(status);
        return new GetOrdersResponseDTO(OrderMapper.mapToDto(orders), 1, 10);
    }
}
