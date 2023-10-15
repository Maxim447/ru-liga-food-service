package ru.liga.deliveryservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Schema(description = "DTO для получения доставок")
@Data
@AllArgsConstructor
public class GetDeliveriesResponseDTO {

    @Schema(description = "Доставки")
    private List<DeliveryDTO> deliveries;

    @Schema(description = "Индекс страницы")
    @JsonProperty("page_index")
    private Integer pageIndex;

    @Schema(description = "Счетчик страницы")
    @JsonProperty("page_count")
    private Integer pageCount;
}
