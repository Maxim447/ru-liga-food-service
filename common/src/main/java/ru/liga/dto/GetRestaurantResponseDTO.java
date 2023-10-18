package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetRestaurantResponseDTO {

    private List<FullRestaurantDTO> restaurantDTO;

    @Schema(description = "Индекс страницы")
    @JsonProperty("page_index")
    private Integer pageIndex;

    @Schema(description = "Счетчик страницы")
    @JsonProperty("page_count")
    private Integer pageCount;
}
