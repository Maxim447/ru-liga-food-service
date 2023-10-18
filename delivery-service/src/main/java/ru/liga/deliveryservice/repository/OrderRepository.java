package ru.liga.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.deliveryservice.dto.DeliveryStatus;
import ru.liga.deliveryservice.entity.Order;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderById(Long id);

    List<Order> getAllByStatus(String status);
}
