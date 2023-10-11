package ru.liga.kitchenservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.kitchenservice.model.MenuItems;

@Data
@AllArgsConstructor
public class OrderDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private MenuItems menuItems;
}
