package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.orderservice.mapper.RestaurantMenuItemMapper;
import ru.liga.orderservice.repository.RestaurantMenuItemRepository;

@Service
@RequiredArgsConstructor
public class RestaurantMenuItemService {

    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    public RestaurantMenuItemsDTO getMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        return RestaurantMenuItemMapper.mapToDto(restaurantMenuItem);
    }

    public void deleteMenuItemById(Long id) {
        restaurantMenuItemRepository.deleteById(id);
    }

    public ResponseEntity<?> changeItemPrice(Long id, Integer price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        restaurantMenuItem.setPrice(price);
        restaurantMenuItemRepository.save(restaurantMenuItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
