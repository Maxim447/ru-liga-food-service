package ru.liga.orderservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.*;
import ru.liga.orderservice.service.OrderService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;


@Tag(name = "API для оформления заказов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {


    /**
     * Сервис для работы с заказами
     */
    private final OrderService orderService;

    @Operation(summary = "Получить все заказы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/")
    private ResponseEntity<GetResponseDTO<OrderDTO>> getAllOrders(@PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Positive @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return orderService.getAllOrders(PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Получить заказ по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "Order with this id not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    private ResponseEntity<?> gerOrdersById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping("/")
    private ResponseEntity<?> createOrder(@RequestBody OrderCreationDTO orderCreationDto) {
        return orderService.createOrder(orderCreationDto);
    }

    @Operation(summary = "Оплатить заказ")
    @PutMapping("/{id}/pay")
    public ResponseEntity<?> paymentOrder(@PathVariable UUID id) {
        return orderService.paymentOrder(id);
    }

    @Operation(summary = "Принять заказ")
    @PutMapping("/{uuid}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable("uuid") UUID uuid) {
        return orderService.acceptOrder(uuid);
    }

    @Operation(summary = "Завершить заказ")
    @PutMapping("/{uuid}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable("uuid") UUID uuid) {
        return orderService.completeOrder(uuid);
    }

    @Operation(summary = "Отменить заказ")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable UUID id) {
        return orderService.cancelOrder(id);
    }
}
