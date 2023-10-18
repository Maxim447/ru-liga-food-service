package ru.liga.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
@Builder
public class RestaurantDTO {

    @Schema(description = "Имя")
    private String name;
}