package ru.liga.kitchenservice.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getOrdersByStatus(OrderStatus status, PageRequest pageRequest);
}
