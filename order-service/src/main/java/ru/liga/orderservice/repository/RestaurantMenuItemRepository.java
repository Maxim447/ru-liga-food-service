package ru.liga.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.RestaurantMenuItem;

public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {
}
