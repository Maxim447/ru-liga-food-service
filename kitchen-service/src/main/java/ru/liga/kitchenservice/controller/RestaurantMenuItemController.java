package ru.liga.kitchenservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.kitchenservice.service.RestaurantMenuItemService;

@RestController
@RequiredArgsConstructor
public class RestaurantMenuItemController {

    private final RestaurantMenuItemService restaurantMenuItemService;

    @GetMapping("/restaurantItem/{id}")
    public RestaurantMenuItemsDTO getRestaurantItemById(@PathVariable("id") Long id) {
        return restaurantMenuItemService.getMenuItemById(id);
    }
    @PutMapping("/restaurantItem/{id}")
    public ResponseEntity<?> changeRestaurantItemPrice(@PathVariable("id") Long id, @RequestBody Double price) {
        return restaurantMenuItemService.changeItemPrice(id, price);
    }
}