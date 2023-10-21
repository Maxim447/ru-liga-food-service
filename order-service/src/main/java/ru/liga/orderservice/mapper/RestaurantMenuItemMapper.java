package ru.liga.orderservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.entity.RestaurantMenuItem;

@UtilityClass

public class RestaurantMenuItemMapper {

    public RestaurantMenuItemsDTO mapToDto(RestaurantMenuItem restaurantMenuItem) {
        return RestaurantMenuItemsDTO.builder()
                .id(restaurantMenuItem.getId())
                .restaurantId(restaurantMenuItem.getId())
                .name(restaurantMenuItem.getName())
                .price(restaurantMenuItem.getPrice())
                .image(restaurantMenuItem.getImage())
                .description(restaurantMenuItem.getDescription())
                .build();
    }
}
