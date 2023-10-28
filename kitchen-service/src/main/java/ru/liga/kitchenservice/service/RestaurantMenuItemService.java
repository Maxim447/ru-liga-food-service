package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.kitchenservice.repository.RestaurantMenuItemRepository;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RestaurantMenuItemService {

    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    private final AbstractMapper<RestaurantMenuItem, RestaurantMenuItemsDTO> restaurantMenuItemMapper;
    public RestaurantMenuItemsDTO getMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        return restaurantMenuItemMapper.toDto(restaurantMenuItem);
    }

    public ResponseEntity<?> changeItemPrice(Long id, Double price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        restaurantMenuItem.setPrice(BigDecimal.valueOf(price));
        restaurantMenuItemRepository.save(restaurantMenuItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}