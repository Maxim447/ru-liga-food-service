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
import ru.liga.mapper.abstraction.AbstractMapper;


@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AbstractMapper<Restaurant, FullRestaurantDTO> restaurantMapper;

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

    public GetResponseDTO<FullRestaurantDTO> getRestaurants(PageRequest pageRequest) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageRequest);
        return new GetResponseDTO<>(restaurantMapper.toDto(restaurants.getContent()), pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    public FullRestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        return restaurantMapper.toDto(restaurant);
    }
}
