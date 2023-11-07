package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Предметы")
public class ItemsDTO {

    private Double price;

    private Integer quantity;

    private String description;

    private String image;
}