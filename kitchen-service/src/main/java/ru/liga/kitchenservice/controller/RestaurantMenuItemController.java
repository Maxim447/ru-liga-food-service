package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.kitchenservice.service.RestaurantMenuItemService;

@Tag(name = "API для работы с меню")
@RestController
@RequiredArgsConstructor
public class RestaurantMenuItemController {

    /**
     * Сервис для RestaurantMenuItemController
     */
    private final RestaurantMenuItemService restaurantMenuItemService;

    @Operation(summary = "Получить товар меню по id")
    @GetMapping("/restaurantItem/{id}")
    public RestaurantMenuItemsDTO getRestaurantItemById(@PathVariable("id") Long id) {
        return restaurantMenuItemService.getMenuItemById(id);
    }
    @Operation(summary = "Изменить товар меню по id")
    @PutMapping("/restaurantItem/{id}")
    public ResponseEntity<?> changeRestaurantItemPrice(@PathVariable("id") Long id, @RequestBody Double price) {
        return restaurantMenuItemService.changeItemPrice(id, price);
    }
}