package ru.liga.orderservice.mapper;


import lombok.experimental.UtilityClass;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Order;


@UtilityClass
public class OrderMapper {

    public static OrderDTO mapToDto(Order order, RestaurantDTO restaurantDto) {
        return OrderDTO.builder()
                .id(order.getId())
                .restaurant(restaurantDto)
                .timestamp(order.getTimestamp())
                .build();
    }
}
