package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.MenuItemsDTO;
import ru.liga.entity.OrderItem;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenOrderItemMapper extends AbstractMapper<OrderItem, MenuItemsDTO> {

    @Mapping(source = "restaurantMenuItem.id", target = "menuItemId")
    @Mapping(source = "quantity", target = "quantity")
    MenuItemsDTO toDto(OrderItem orderItem);

    List<MenuItemsDTO> toDto(List<OrderItem> entity);
}
