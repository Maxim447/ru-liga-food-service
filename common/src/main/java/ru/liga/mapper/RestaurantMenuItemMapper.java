package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapper.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface RestaurantMenuItemMapper extends AbstractMapper<RestaurantMenuItem, RestaurantMenuItemsDTO> {
    @Mapping(source = "restaurantId.id", target = "restaurantId")
    RestaurantMenuItemsDTO toDto(RestaurantMenuItem restaurantMenuItem);

}
