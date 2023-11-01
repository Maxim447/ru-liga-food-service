package ru.liga.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Описание заказа")
public class KitchenOrderDTO {

    private Long id;

    @JsonProperty("menu_items")
    private List<MenuItemsDTO> menuItems;
}