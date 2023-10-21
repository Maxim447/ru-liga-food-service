package ru.liga.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.orderservice.service.RestaurantMenuItemService;

@RestController
@RequiredArgsConstructor
public class RestaurantMenuItemController {

    private final RestaurantMenuItemService restaurantMenuItemService;
    @GetMapping("/restaurantItem/{id}")
    public RestaurantMenuItemsDTO getRestaurantItemById(@PathVariable("id") Long id) {
        return restaurantMenuItemService.getMenuItemById(id);
    }

//    @DeleteMapping("/restaurantItem/{id}")
//    public void deleteOrderItemById(@PathVariable("id") Long id) {
//        restaurantMenuItemService.deleteMenuItemById(id);
//    }
    @PutMapping("/restaurantItem/{id}")
    public ResponseEntity<?> changeResItemPrice(@PathVariable("id") Long id, @RequestBody Integer price) {
        return restaurantMenuItemService.changeItemPrice(id,price);
    }
}
