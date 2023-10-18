package ru.liga.orderservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.dto.OrderConfirmationDTO;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.orderservice.service.OrderService;


@Tag(name = "API для оформления заказов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Schema(description = "Сервис для OrderController")
    private final OrderService orderService;

    @Operation(summary = "Получить все заказы")
    @GetMapping("/")
    private GetOrdersResponseDTO getOrders() {
        return orderService.getOrders();
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
