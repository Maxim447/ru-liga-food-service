package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FullRestaurantDTO {

    private Long id;
    private String name;

    private String address;

    private String status;

    private BigDecimal longitude;

    private BigDecimal latitude;
}
