package ru.liga.orderservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.dto.CustomerRestaurantDTO;

@UtilityClass
public class RestaurantMapper {

    public CustomerRestaurantDTO mapToDto(String name) {
        return new CustomerRestaurantDTO(name);
    }
}
