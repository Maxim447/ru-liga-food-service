package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestaurantMenuItemsDTO {

    private Long id;

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    private String name;

    private Double price;

    private String image;

    private String description;
}
