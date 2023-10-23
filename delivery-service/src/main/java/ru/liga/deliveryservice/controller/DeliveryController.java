package ru.liga.deliveryservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.deliveryservice.clients.KitchenClient;
import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetDeliveriesResponseDTO;
import ru.liga.deliveryservice.service.DeliveryService;
import ru.liga.dto.GetRestaurantResponseDTO;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequiredArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {

    private final KitchenClient kitchenClient;

    @Schema(description = "Сервис для отправки заказов курьерам")
    private final DeliveryService deliveryService;

    @GetMapping("/restaurant")
    public GetRestaurantResponseDTO getRestaurants (@RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return kitchenClient.getRestaurants(pageIndex, pageCount);
    }
    @Operation(summary = "Получить все доставки")
    @GetMapping("/")
    public GetDeliveriesResponseDTO getDeliveriesByStatus(@RequestParam("status") String status,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return deliveryService.getDeliveriesByStatus(status, PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Создать доставку")
    @PostMapping("/{id}")
    public ResponseEntity<?> createDelivery(@RequestBody ActionDTO actionDTO, @PathVariable Long id) {
        return deliveryService.setDeliveryAction(id, actionDTO);
    }
}
