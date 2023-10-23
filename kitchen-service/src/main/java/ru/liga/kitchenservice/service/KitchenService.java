package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.KitchenOrderDTO;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.kitchenservice.clients.KitchenClient;
import ru.liga.kitchenservice.mapper.OrderMapper;
import ru.liga.kitchenservice.repository.OrderRepository;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@RequiredArgsConstructor
@Service
public class KitchenService {

    private final OrderRepository orderRepository;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final KitchenClient kitchenClient;

    @Operation(summary = "Получить все заказы")
    public GetResponseDTO<KitchenOrderDTO> getOrdersByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.getOrdersByStatus(status, pageRequest);
        return new GetResponseDTO<>(OrderMapper.mapToDto(orders), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }


    public void acceptOrder(Long orderId, ActionDTO actionDTO) {
        kitchenClient.updateOrderStatus(orderId, actionDTO);
    }

    public void denyOrder(Long orderId, ActionDTO actionDTO) {
        kitchenClient.updateOrderStatus(orderId, actionDTO);
    }

    public void finishOrder(Long orderId, String routingKey) {
        rabbitMQProducerService.sendMessage(String.valueOf(orderId), routingKey);
    }
}
