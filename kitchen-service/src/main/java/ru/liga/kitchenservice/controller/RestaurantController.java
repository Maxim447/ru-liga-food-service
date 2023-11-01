package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.RestaurantCreationDTO;
import ru.liga.kitchenservice.service.RestaurantService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Tag(name = "API для работы с ресторанами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    /**
     * Сервис для RestaurantController
     */
    private final RestaurantService restaurantService;

    @Operation(summary = "Создать ресторан")
    @PostMapping("/create")
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantCreationDTO restaurantCreationDTO) {
        return restaurantService.createRestaurant(restaurantCreationDTO);
    }

    @Operation(summary = "Получить все рестораны")
    @GetMapping("/")
    public GetResponseDTO<FullRestaurantDTO> getRestaurants (@PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
            @Positive @RequestParam(required = false, defaultValue = "10") Integer pageCount) {
        return restaurantService.getRestaurants(PageRequest.of(pageIndex, pageCount));
    }

    @Operation(summary = "Получить ресторан по id")
    @GetMapping("/{id}")
    public FullRestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }
}
