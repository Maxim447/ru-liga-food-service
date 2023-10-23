package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetResponseDTO<T> {


    private List<T> content;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_count")
    private Integer pageCount;
}
