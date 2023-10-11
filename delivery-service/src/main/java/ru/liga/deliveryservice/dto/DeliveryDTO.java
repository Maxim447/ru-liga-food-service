package ru.liga.deliveryservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.deliveryservice.model.Customer;
import ru.liga.deliveryservice.model.Restaurant;

@Schema(description = "DTO доставок")
@Data
@AllArgsConstructor
public class DeliveryDTO {

    @Schema(description = "Id заказа")
    @JsonProperty("order_id")
    private Long orderId;

    @Schema(description = "Ресторан")
    private Restaurant restaurant;

    @Schema(description = "Заказчик")
    private Customer customer;

    @Schema(description = "Способ оплаты")
    private String payment;
}
