package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Schema(description = "DTO для создания запроса заказа")
@Data
@Builder
public class OrderCreationDTO {

    @Schema(description = "Id ресторана")
    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private List<MenuItemsDTO> menuItems;
}
