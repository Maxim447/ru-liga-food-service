package ru.liga.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.orderservice.entity.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

}
