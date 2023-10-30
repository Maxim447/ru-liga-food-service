package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.abstraction.AbstractMapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends AbstractMapper<Restaurant, FullRestaurantDTO> {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    FullRestaurantDTO toDto(Restaurant restaurant);
}
