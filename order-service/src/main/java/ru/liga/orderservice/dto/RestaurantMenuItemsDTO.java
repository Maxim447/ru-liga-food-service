package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    private Integer price;

    private String image;

    private String description;
}
