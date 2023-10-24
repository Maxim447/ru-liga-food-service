package ru.liga.kitchenservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entity.RestaurantMenuItem;

import java.util.Optional;

@Repository
public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {
    Optional<RestaurantMenuItem> getRestaurantMenuItemById(Long id);
}