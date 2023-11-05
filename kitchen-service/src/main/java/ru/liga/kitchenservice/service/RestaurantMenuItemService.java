package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestaurantMenuItemsDTO;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.kitchenservice.repository.RestaurantMenuItemRepository;
import ru.liga.mapper.RestaurantMenuItemMapper;

import java.math.BigDecimal;

/**
 * Сервис для работы с меню ресторана
 */
@Service
@RequiredArgsConstructor
public class RestaurantMenuItemService {

    /**
     * Репозиторий для работы с базой днаыых restaurant_menu_items
     */
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;

    /**
     * Маппер для преобразования сущности RestaurantMenuItem в RestaurantMenuItemsDTO
     */
    private final RestaurantMenuItemMapper restaurantMenuItemMapper;

    /**
     * Получить предмет меню по id
     */
    public RestaurantMenuItemsDTO getMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        return restaurantMenuItemMapper.toDto(restaurantMenuItem);
    }

    /**
     * Изменить цену предмета
     */
    public ResponseEntity<?> changeItemPrice(Long id, Double price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.getRestaurantMenuItemById(id).orElseThrow();
        restaurantMenuItem.setPrice(BigDecimal.valueOf(price));
        restaurantMenuItemRepository.save(restaurantMenuItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}