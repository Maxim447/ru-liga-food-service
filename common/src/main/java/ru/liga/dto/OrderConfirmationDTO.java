package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Schema(description = "DTO для ответа после создания заказа")
@Data
@AllArgsConstructor
public class OrderConfirmationDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Секретная ссылка для оплаты")
    @JsonProperty("secret_payment_url")
    private String secretPaymentUrl;

    @Schema(description = "Ожидаемое время доставки")
    @JsonProperty("estimated_time_of_arrival")
    private Date estimatedTimeOfArrival;
}