package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO доставок")
@Data
@AllArgsConstructor
public class DeliveryDTO {

    @Schema(description = "Id заказа")
    @JsonProperty("order_id")
    private Long orderId;

    @Schema(description = "Ресторан")
    private DeliveryRestaurantDTO restaurant;

    @Schema(description = "Заказчик")
    private CustomerDTO customer;

    @Schema(description = "Способ оплаты")
    private Double payment;
}