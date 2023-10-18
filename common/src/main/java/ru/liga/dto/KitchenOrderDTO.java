package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KitchenOrderDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private List<MenuItemsDTO> menuItems;
}