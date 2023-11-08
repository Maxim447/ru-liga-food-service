package ru.liga.deliveryservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.*;
import ru.liga.deliveryservice.service.DeliveryService;
import ru.liga.entity.enums.OrderStatus;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    /**
     * Сервис для отправки заказов курьерам
     */
    private final DeliveryService deliveryService;

    @Operation(summary = "Получение списка доступных заказов")
    @GetMapping("/")
    public GetResponseDTO<DeliveryDTO> getDeliveriesByStatus(@PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Positive @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return deliveryService.getDeliveriesByStatus(PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Принять заказ")
    @PostMapping("/{courierId}/take/{id}")
    public void takeOrder(@PathVariable Long courierId,@PathVariable UUID id) {
        deliveryService.takeOrder(courierId, id);
    }

    @Operation(summary = "Завершить заказ")
    @PostMapping("/complete/{id}")
    public ResponseEntity<?> completeOrder(@PathVariable UUID id) {
        return deliveryService.complete(id);
    }
}
