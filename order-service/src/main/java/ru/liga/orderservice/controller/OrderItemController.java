package ru.liga.orderservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.ItemsDTO;
import ru.liga.orderservice.service.OrderItemService;

@Tag(name = "API для работы с товарами доставки")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemController {


    /**
     * Сервис для работы с содержимым заказа
     */
    private final OrderItemService orderItemService;

    @Operation(summary = "Получить по id")
    @GetMapping("/{id}")
    public ItemsDTO getOrderItemById(@PathVariable("id") Long id) {
        return orderItemService.getItemById(id);
    }

    @Operation(summary = "Удалить по id")
    @DeleteMapping("/{id}")
    public void deleteOrderItemById(@PathVariable("id") Long id) {
        orderItemService.deleteItemById(id);
    }
}
