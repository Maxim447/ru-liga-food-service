package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemsDTO {

    @Schema(description = "Цена")
    private Integer price;

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Картика")
    private String image;
}