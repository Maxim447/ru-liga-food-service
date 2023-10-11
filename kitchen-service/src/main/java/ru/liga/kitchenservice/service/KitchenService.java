package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;
import ru.liga.kitchenservice.dto.OrderDTO;
import ru.liga.kitchenservice.model.MenuItems;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@Service
public class KitchenService {

    @Operation(summary = "Получить все заказы")
    public List<OrderDTO> getOrders(String status) {
        return List.of(
                new OrderDTO(1L, new MenuItems(10, 1)),
                new OrderDTO(2L, new MenuItems(1, 2))
        );
    }
}
