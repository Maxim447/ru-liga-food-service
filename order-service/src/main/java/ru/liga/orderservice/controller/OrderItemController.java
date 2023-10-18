package ru.liga.orderservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.ItemsDTO;
import ru.liga.orderservice.service.OrderItemService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ItemsDTO getOrderItemById(@PathVariable("id") Long id) {
        return orderItemService.getItemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItemById(@PathVariable("id") Long id) {
        orderItemService.deleteItemById(id);
    }
}
