package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.KitchenOrderDTO;
import ru.liga.dto.MenuItemsDTO;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.kitchenservice.clients.KitchenClient;
import ru.liga.kitchenservice.repository.OrderRepository;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
public class KitchenService {

    private final OrderRepository orderRepository;

    private final RabbitMQProducerService rabbitMQProducerService;

    private final KitchenClient kitchenClient;

    private final AbstractMapper<OrderItem, MenuItemsDTO> kitchenOrderItemMapper;
    @Operation(summary = "Получить все заказы")
    public GetResponseDTO<KitchenOrderDTO> getOrdersByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.getOrdersByStatus(status, pageRequest);
        List<KitchenOrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            List<MenuItemsDTO> menuItemsDTOList = kitchenOrderItemMapper.toDto(order.getItems());
            orderDTOList.add(new KitchenOrderDTO(order.getId(), menuItemsDTOList));
        }
        return new GetResponseDTO<>(orderDTOList, pageRequest.getPageNumber(), pageRequest.getPageSize());
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
