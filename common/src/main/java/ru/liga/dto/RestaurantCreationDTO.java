package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RestaurantCreationDTO {

    private String name;

    private String address;

    private String status;

    private BigDecimal longitude;

    private BigDecimal latitude;
}
