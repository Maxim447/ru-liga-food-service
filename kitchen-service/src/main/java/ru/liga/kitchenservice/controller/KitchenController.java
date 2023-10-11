package ru.liga.kitchenservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.kitchenservice.dto.OrderDTO;
import ru.liga.kitchenservice.service.KitchenService;

import java.util.List;

@Tag(name = "API для работы с рестараном")
@RestController
@RequestMapping("/")
public class KitchenController {

    @Schema(description = "Сервис для KitchenController")
    private final KitchenService kitchenService;

    @Autowired
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @Operation(summary = "Получить все заказы")
    @GetMapping("/orders")
    public List<OrderDTO> getOrders(@RequestParam(value = "status") String status) {
        return kitchenService.getOrders(status);
    }
}
