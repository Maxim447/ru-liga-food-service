package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends AbstractMapper<Order, OrderDTO> {

    @Mapping(source = "restaurantId.name", target = "customerRestaurantDTO.name")
    OrderDTO toDto(Order order);

    List<OrderDTO> toDto(List<Order> orders);
}
