package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Общее DTO для отображение данных со страницами")
public class GetResponseDTO<T> {


    private List<T> content;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_count")
    private Integer pageCount;
}
