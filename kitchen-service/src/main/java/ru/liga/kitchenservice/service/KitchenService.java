package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.KitchenOrderDTO;
import ru.liga.dto.MenuItemsDTO;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.kitchenservice.clients.KitchenClient;
import ru.liga.kitchenservice.repository.OrderRepository;
import ru.liga.mapper.KitchenOrderItemMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для приема заказов на кухне
 */
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
public class KitchenService {

    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    /**
     * Сервис для отправки сообщений RabbitMQ
     */
    private final RabbitMQProducerService rabbitMQProducerService;

    /**
     * Feign клиент для общения с delivery-service
     */
    private final KitchenClient kitchenClient;

    /**
     * Маппер для преобразования сущности OrderItem в MenuItemsDTO
     */
    private final KitchenOrderItemMapper kitchenOrderItemMapper;

    /**
     * Получить все заказы
     */
    public GetResponseDTO<KitchenOrderDTO> getOrdersByStatus(OrderStatus status, PageRequest pageRequest) {
        List<Order> orders = orderRepository.getOrdersByStatus(status, pageRequest);
        List<KitchenOrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            List<MenuItemsDTO> menuItemsDTOList = kitchenOrderItemMapper.toDto(order.getItems());
            orderDTOList.add(new KitchenOrderDTO(order.getId(), menuItemsDTOList));
        }
        return new GetResponseDTO<>(orderDTOList, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
    /**
     * Принять или отклонить заказ
     */
    public void actionWithOrder(Long orderId, ActionDTO actionDTO) {
        kitchenClient.updateOrderStatus(orderId, actionDTO);
    }

    /**
     * Завершить заказ
     */
    public void finishOrder(Long orderId, String routingKey) {
        rabbitMQProducerService.sendMessage(String.valueOf(orderId), routingKey);
    }
}
