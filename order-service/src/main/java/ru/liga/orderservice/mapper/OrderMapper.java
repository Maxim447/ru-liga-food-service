package ru.liga.orderservice.mapper;


import lombok.experimental.UtilityClass;
import ru.liga.dto.CustomerRestaurantDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Order;


@UtilityClass
public class OrderMapper {

    public static OrderDTO mapToDto(Order order, CustomerRestaurantDTO customerRestaurantDTO) {
        return OrderDTO.builder()
                .id(order.getId())
                .customerRestaurantDTO(customerRestaurantDTO)
                .timestamp(order.getTimestamp())
                .build();
    }
}
