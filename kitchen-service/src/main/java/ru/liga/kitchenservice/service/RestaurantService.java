package ru.liga.kitchenservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.dto.GetResponseDTO;
import ru.liga.dto.RestaurantCreationDTO;
import ru.liga.entity.Restaurant;
import ru.liga.kitchenservice.repository.RestaurantRepository;
import ru.liga.mapper.RestaurantMapper;

/**
 * Сервис для работы с ресторанами
 */
@Service
@RequiredArgsConstructor
public class RestaurantService {

    /**
     * Репозиторий для работы с базой днаыых restaurants
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Маппер для преобразования сущности Restaurant в FullRestaurantDTO
     */
    private final RestaurantMapper restaurantMapper;

    /**
     * Создать ресторан
     */
    public ResponseEntity<?> createRestaurant(RestaurantCreationDTO restaurantCreationDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantCreationDTO.getAddress());
        restaurant.setName(restaurantCreationDTO.getName());
        restaurant.setStatus(restaurantCreationDTO.getStatus());
        restaurant.setLongitude(restaurantCreationDTO.getLongitude());
        restaurant.setLatitude(restaurantCreationDTO.getLatitude());
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok().build();
    }

    /**
     * Получить все рестораны
     */
    public GetResponseDTO<FullRestaurantDTO> getRestaurants(PageRequest pageRequest) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);
        return new GetResponseDTO<>(restaurantMapper.toDto(restaurants.getContent()), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    /**
     * Получить ресторан по id
     */
    public FullRestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return restaurantMapper.toDto(restaurant);
    }
}
