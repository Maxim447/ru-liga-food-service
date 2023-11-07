package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.entity.enums.RestaurantStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Schema(description = "Создание ресторана")
public class RestaurantCreationDTO {

    private String name;

    private String address;

    private RestaurantStatus status;

    private BigDecimal longitude;

    private BigDecimal latitude;
}
