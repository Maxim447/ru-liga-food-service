package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.ItemsDTO;
import ru.liga.entity.OrderItem;
import ru.liga.mapper.abstraction.AbstractMapper;
import ru.liga.orderservice.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final AbstractMapper<OrderItem, ItemsDTO> orderItemMapper;
    public ItemsDTO getItemById(Long id) {
        OrderItem orderItem = orderItemRepository.getOrderItemById(id).orElseThrow();
        return orderItemMapper.toDto(orderItem);
    }

    public void deleteItemById(long id) {
        orderItemRepository.deleteById(id);
    }
}
