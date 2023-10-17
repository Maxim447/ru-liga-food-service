package ru.liga.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;
import java.util.List;


@Schema(description = "DTO для получения заказа")
@Data
@AllArgsConstructor
public class OrderDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Ресторан")
    private RestaurantDTO restaurant;

    @Schema(description = "Время")
    private Date timestamp;

    @Schema(description = "Список товаров в заказе")
    private List<ItemsDTO> items;
}

