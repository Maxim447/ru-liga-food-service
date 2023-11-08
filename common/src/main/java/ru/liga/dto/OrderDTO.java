package ru.liga.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
@Schema(description = "DTO для получения заказа")
public class OrderDTO {

    private UUID id;

    @JsonProperty("restaurant")
    private CustomerRestaurantDTO customerRestaurantDTO;

    private Date timestamp;

    private List<ItemsDTO> items;
}