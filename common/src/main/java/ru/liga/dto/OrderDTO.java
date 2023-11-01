package ru.liga.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@Builder
@Schema(description = "DTO для получения заказа")
public class OrderDTO {

    private Long id;

    @JsonProperty("restaurant")
    private CustomerRestaurantDTO customerRestaurantDTO;

    private Date timestamp;

    private List<ItemsDTO> items;
}