package ru.liga.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
public class RestaurantDTO {

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Расстояние")
    private Double distance;
}