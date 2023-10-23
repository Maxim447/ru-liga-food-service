package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.entity.enums.RestaurantStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FullRestaurantDTO {

    private Long id;
    private String name;

    private String address;

    private RestaurantStatus status;

    private BigDecimal longitude;

    private BigDecimal latitude;
}
