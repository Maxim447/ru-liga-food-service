package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "DTO для создания запроса заказа")
public class OrderCreationDTO {

    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("menu_items")
    private List<MenuItemsDTO> menuItems;
}
