package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;
import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.dto.MenuItemsDTO;
import ru.liga.dto.KitchenOrderDTO;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@Service
public class KitchenService {

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders(String status) {
        return new GetOrdersResponseDTO(List.of(
                new KitchenOrderDTO(1L, List.of(new MenuItemsDTO(10, 1))),
                new KitchenOrderDTO(2L, List.of(new MenuItemsDTO(1, 2)))),
                1,
                10
        );
    }
}
