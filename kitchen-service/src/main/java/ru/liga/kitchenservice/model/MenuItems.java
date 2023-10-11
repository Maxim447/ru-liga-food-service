package ru.liga.kitchenservice.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItems {

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Id товара в меню")
    private Integer menuItemId;
}
