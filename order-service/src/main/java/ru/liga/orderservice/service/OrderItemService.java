package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.ItemsDTO;
import ru.liga.entity.OrderItem;
import ru.liga.mapper.abstraction.AbstractMapper;
import ru.liga.orderservice.repository.OrderItemRepository;

/**
 * Сервис для работы с содержимым заказа
 */
@Service
@RequiredArgsConstructor
public class OrderItemService {


    /**
     * Репозиторий для работы с базой днаыых order_items
     */
    private final OrderItemRepository orderItemRepository;


    /**
     * Маппер для преобразования сущности OrderItem в ItemsDTO
     */
    private final AbstractMapper<OrderItem, ItemsDTO> orderItemMapper;

    /**
     * Получить сущность orderItem по id
     */
    public ItemsDTO getItemById(Long id) {
        OrderItem orderItem = orderItemRepository.getOrderItemById(id).orElseThrow();
        return orderItemMapper.toDto(orderItem);
    }

    /**
     * Удалить сущность orderItem по id
     */
    public void deleteItemById(long id) {
        orderItemRepository.deleteById(id);
    }
}
