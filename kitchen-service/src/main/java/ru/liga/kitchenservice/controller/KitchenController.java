package ru.liga.kitchenservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.kitchenservice.service.KitchenService;

import java.util.UUID;

@Tag(name = "API для работы с рестараном")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchen")
public class KitchenController {


    /**
     * Сервис для KitchenController
     */
    private final KitchenService kitchenService;


    @Operation(summary = "Взять заказ")
    @PostMapping("/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable UUID id) {
        return kitchenService.acceptOrder(id);
    }

    @Operation(summary = "Отклонить заказ")
    @PostMapping("/{id}/decline")
    public ResponseEntity<?> decline(@PathVariable UUID id) {
        return kitchenService.declineOrder(id);
    }

    @Operation(summary = "Завершить заказ")
    @PostMapping("/{id}/ready")
    public ResponseEntity<?> ready(@PathVariable UUID id) {
        return kitchenService.readyOrder(id);
    }
}
