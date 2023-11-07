package ru.liga.mapper;

import org.mapstruct.Mapper;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AbstractMapper<Restaurant, FullRestaurantDTO> {

    FullRestaurantDTO toDto(Restaurant restaurant);
}
