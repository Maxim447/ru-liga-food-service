package ru.liga.kitchenservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.GetKitchenOrdersResponseDTO;
import ru.liga.kitchenservice.service.KitchenService;

@Tag(name = "API для работы с рестараном")
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class KitchenController {

    @Schema(description = "Сервис для KitchenController")
    private final KitchenService kitchenService;

    @Operation(summary = "Получить все заказы")
    @GetMapping("/orders")
    public GetKitchenOrdersResponseDTO getOrders(@RequestParam(value = "status") String status,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return kitchenService.getOrdersByStatus(status, PageRequest.of(pageIndex, pageCount));
    }
}
