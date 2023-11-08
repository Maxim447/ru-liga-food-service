package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
@Schema(description = "DTO для ответа после создания заказа")
public class OrderConfirmationDTO {

    private UUID id;

    @JsonProperty("secret_payment_url")
    private String secretPaymentUrl;

    @JsonProperty("estimated_time_of_arrival")
    private Date estimatedTimeOfArrival;
}