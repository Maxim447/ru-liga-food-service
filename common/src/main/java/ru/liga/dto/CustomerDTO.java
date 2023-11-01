package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO заказчика")
@Data
@AllArgsConstructor
public class CustomerDTO {

    private String address;

    private Double distance;
}