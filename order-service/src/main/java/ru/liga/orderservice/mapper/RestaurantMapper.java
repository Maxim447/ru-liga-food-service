package ru.liga.orderservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.orderservice.dto.RestaurantDTO;

@UtilityClass
public class RestaurantMapper {

    public RestaurantDTO mapToDto(String name) {
        return new RestaurantDTO(name);
    }
}
