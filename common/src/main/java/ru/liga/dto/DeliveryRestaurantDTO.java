package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
public class DeliveryRestaurantDTO {

    private String address;

    private Double distance;
}