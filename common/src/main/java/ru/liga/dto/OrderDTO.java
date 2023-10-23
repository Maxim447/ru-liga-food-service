package ru.liga.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Schema(description = "DTO для получения заказа")
@Data
@AllArgsConstructor
@Builder
public class OrderDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Ресторан")
    @JsonProperty("restaurant")
    private CustomerRestaurantDTO customerRestaurantDTO;

    @Schema(description = "Время")
    private Date timestamp;

    @Schema(description = "Список товаров в заказе")
    private List<ItemsDTO> items;
}