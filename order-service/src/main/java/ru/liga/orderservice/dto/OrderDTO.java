package ru.liga.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.orderservice.model.Items;
import ru.liga.orderservice.model.Restaurant;

import java.util.List;


@Schema(description = "DTO для получения заказа")
@Data
@AllArgsConstructor
public class OrderDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Ресторан")
    private Restaurant restaurant;

    @Schema(description = "Список товаров в заказе")
    private List<Items> items;
}

