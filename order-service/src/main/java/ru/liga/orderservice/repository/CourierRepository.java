package ru.liga.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
