package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.mapper.abstraction.AbstractMapper;
import ru.liga.orderservice.repository.OrderRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Сервис для работы с заказами
 */
@Schema(description = "Сервис для оформления заказов")
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
public class OrderService {


    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    /**
     * Маппер для преобразования сущности Order в OrderDTO
     */
    private final AbstractMapper<Order, OrderDTO> orderMapper;

    /**
     * Маппер для преобразования сущности OrderItem в ItemsDTO
     */
    private final AbstractMapper<OrderItem, ItemsDTO> itemsMapper;

    /**
     * Получить все заказы
     */
    public ResponseEntity<GetResponseDTO<OrderDTO>> getAllOrders(PageRequest pageRequest) {
        Page<Order> orders = orderRepository.findAll(pageRequest);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {

            OrderDTO orderDTO = orderMapper.toDto(order);
            List<ItemsDTO> items = itemsMapper.toDto(order.getItems());

            orderDTO.setItems(items);
            orderDTOList.add(orderDTO);
        }
        return ResponseEntity.ok(new GetResponseDTO<>(orderDTOList, pageRequest.getPageNumber(), pageRequest.getPageSize()));
    }

    /**
     * Получить заказ по id"
     */
    public ResponseEntity<?> getOrderById(Long id) {
        OrderDTO orderDTO;
        try {
            Order order = orderRepository.getOrderById(id)
                    .orElseThrow(
                            () -> new NoSuchElementException("There is no order with id:" + id)
                    );
            orderDTO = orderMapper.toDto(order);
            List<ItemsDTO> items = itemsMapper.toDto(order.getItems());
            orderDTO.setItems(items);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDTO);
    }


    /**
     * Создать новый заказ
     */
    public OrderConfirmationDTO createOrder(OrderCreationDTO orderCreationDto) {
        return new OrderConfirmationDTO(1L, "https://secretPaymentUrl.com", Date.from(Instant.now().plusSeconds(100)));
    }
}
