package ru.liga.deliveryservice.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Модель ресторана")
@Data
@AllArgsConstructor
public class Restaurant {

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Расстояние")
    private String distance;
}
