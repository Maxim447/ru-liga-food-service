package ru.liga.orderservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.orderservice.repository.OrderRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest()
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testGetOrderById_ExistingOrder() {

        Order mockOrder = new Order();
        when(orderRepository.getOrderById(1L)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<?> responseEntity = orderService.getOrderById(1L);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getBody() instanceof OrderDTO);
    }

    @Test
    public void testGetOrderById_NonExistingOrder() {
        when(orderRepository.getOrderById(200L)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = orderService.getOrderById(2L);

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());

        verify(orderRepository, times(1)).getOrderById(2L);
    }
}
