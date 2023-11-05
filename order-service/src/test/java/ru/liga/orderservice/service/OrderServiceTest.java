package ru.liga.orderservice.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import ru.liga.dto.CustomerRestaurantDTO;
import ru.liga.dto.ItemsDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.orderservice.repository.OrderRepository;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void testGetOrderById_ExistingOrder() {

        Long orderId = 1L;
        Order order = orderRepository.getOrderById(orderId).orElseThrow();
        CustomerRestaurantDTO customerRestaurantDTO = new CustomerRestaurantDTO(order.getRestaurantId().getName());
        List<ItemsDTO> itemsDTOs = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            itemsDTOs.add(new ItemsDTO(item.getPrice().doubleValue(),
                    item.getQuantity(),
                    item.getRestaurantMenuItem().getDescription(),
                    item.getRestaurantMenuItem().getImage())
            );
        }

        OrderDTO orderDTO = new OrderDTO(orderId,
                customerRestaurantDTO,
                Timestamp.valueOf("2023-10-30 19:25:17.005612"),
                itemsDTOs
        );

        ResponseEntity<?> response = orderService.getOrderById(orderId);

        assertNotNull(response);

        assertEquals(ResponseEntity.ok(orderDTO), response);
    }

    @Test
    public void testGetOrderById_NonExistingOrder() {
        ResponseEntity<?> responseEntity = orderService.getOrderById(0L);

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }
}
