package ru.liga.orderservice.mapper;


import lombok.experimental.UtilityClass;
import ru.liga.orderservice.dto.OrderDTO;
import ru.liga.orderservice.dto.RestaurantDTO;
import ru.liga.orderservice.entity.Order;


@UtilityClass
public class OrderMapper {

    public static OrderDTO mapToDto(Order order, RestaurantDTO restaurantDto) {
        OrderDTO orderDTO = OrderDTO.builder()
                .id(order.getId())
                .restaurant(restaurantDto)
                .timestamp(order.getTimestamp())
                .build();
        return orderDTO;
    }
}
