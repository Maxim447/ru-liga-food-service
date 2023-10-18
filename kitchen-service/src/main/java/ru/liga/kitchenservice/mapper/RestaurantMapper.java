package ru.liga.kitchenservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RestaurantMapper {
    public static List<FullRestaurantDTO> mapToDto(List<Restaurant> restaurants) {
        List<FullRestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            FullRestaurantDTO fullRestaurantDTO = new FullRestaurantDTO(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getAddress(),
                    restaurant.getStatus(),
                    restaurant.getLongitude(),
                    restaurant.getLatitude()
            );
            restaurantDTOList.add(fullRestaurantDTO);
        }
        return restaurantDTOList;
    }
}
