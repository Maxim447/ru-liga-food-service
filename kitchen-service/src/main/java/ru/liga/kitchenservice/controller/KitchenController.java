package ru.liga.kitchenservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.KitchenOrderDTO;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.kitchenservice.clients.KitchenClient;
import ru.liga.kitchenservice.service.KitchenService;

@Tag(name = "API для работы с рестараном")
@RestController
@RequiredArgsConstructor
@RequestMapping("/kitchen")
public class KitchenController {

    @Schema(description = "Сервис для KitchenController")
    private final KitchenService kitchenService;

    private final KitchenClient kitchenClient;

    @Operation(summary = "Получить все заказы")
    @GetMapping("/orders")
    public GetResponseDTO<KitchenOrderDTO> getOrders(@RequestParam(value = "status") OrderStatus status,
            @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return kitchenService.getOrdersByStatus(status, PageRequest.of(pageIndex, pageCount));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody ActionDTO actionDTO) {
        return kitchenClient.updateOrderStatus(id, actionDTO);
    }
}
