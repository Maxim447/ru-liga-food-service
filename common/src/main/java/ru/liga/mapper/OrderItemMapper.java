package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.ItemsDTO;
import ru.liga.entity.OrderItem;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends AbstractMapper<OrderItem, ItemsDTO> {

    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "image")
    ItemsDTO toDto(OrderItem orderItem);

    List<ItemsDTO> toDto(List<OrderItem> orderItem);

}
