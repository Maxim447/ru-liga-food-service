package ru.liga.deliveryservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.deliveryservice.dto.ActionDTO;
import ru.liga.deliveryservice.dto.DeliveryStatus;
import ru.liga.deliveryservice.dto.GetDeliveriesResponseDTO;
import ru.liga.deliveryservice.service.DeliveryService;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DeliveryController {

    @Schema(description = "Сервис для отправки заказов курьерам")
    private final DeliveryService deliveryService;

    @Operation(summary = "Получить все доставки")
    @GetMapping("/deliveries")
    public GetDeliveriesResponseDTO getDeliveriesByStatus(@RequestParam("status") String status,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return deliveryService.getDeliveriesByStatus(status, PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Создать доставку")
    @PostMapping("/delivery/{id}")
    public ResponseEntity<?> createDelivery(@RequestBody ActionDTO actionDTO, @PathVariable Long id) {
        return deliveryService.setDeliveryAction(id, actionDTO);
    }
}
