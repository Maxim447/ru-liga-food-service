package ru.liga.orderservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.dto.ItemsDTO;
import ru.liga.entity.OrderItem;

@UtilityClass
public class ItemMapper {

    public static ItemsDTO mapToDto(OrderItem orderItem) {
        return ItemsDTO.builder()
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .description(orderItem.getRestaurantMenuItem().getDescription())
                .image(orderItem.getRestaurantMenuItem().getImage())
                .build();
    }
}
