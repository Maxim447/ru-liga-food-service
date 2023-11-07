package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO доставок")
@Data
@AllArgsConstructor
public class DeliveryDTO {

    @JsonProperty("order_id")
    private Long orderId;

    private DeliveryRestaurantDTO restaurant;

    private CustomerDTO customer;

    private Double payment;
}