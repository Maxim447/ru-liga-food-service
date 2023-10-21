package ru.liga.orderservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.*;
import ru.liga.orderservice.service.OrderService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Tag(name = "API для оформления заказов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Schema(description = "Сервис для OrderController")
    private final OrderService orderService;

    @Operation(summary = "Получить все заказы")
    @GetMapping("/")
    private GetOrdersResponseDTO getAllOrders(@PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Positive @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return orderService.getAllOrders(PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Получить заказ по id")
    @GetMapping("/{id}")
    private OrderDTO gerOrdersById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping("/")
    private OrderConfirmationDTO createOrder(@RequestBody OrderCreationDTO orderCreationDto) {
        return orderService.createOrder(orderCreationDto);
    }
}
