package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Товар в меню")
public class MenuItemsDTO {

    private Integer quantity;

    @JsonProperty("menu_item_id")
    private Long menuItemId;
}