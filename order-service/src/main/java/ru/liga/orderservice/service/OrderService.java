package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.orderservice.dto.*;
import ru.liga.orderservice.entity.Order;
import ru.liga.orderservice.mapper.OrderMapper;
import ru.liga.orderservice.mapper.RestaurantMapper;
import ru.liga.orderservice.repository.OrderRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
public class OrderService {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders() {
        return new GetOrdersResponseDTO(List.of(
                new OrderDTO(1L, new RestaurantDTO("Dodo Pizza"), Date.from(Instant.now()), List.of(new ItemsDTO(980, 1, "Big Pizza", "There will be in the future"))),
                new OrderDTO(2L, new RestaurantDTO("SPAR"), Date.from(Instant.now()), List.of(new ItemsDTO(100, 2, "Something", "There will be in the future")))),
                1,
                10
        );
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.getOrderById(id).orElseThrow();
//        new OrderDTO(id, new RestaurantDTO("Dodo Pizza"), Date.from(Instant.now()), List.of(new ItemsDTO(980.00, 1, "Big Pizza", "There will be in the future")));
        OrderDTO orderDTO = OrderMapper.mapToDto(order, RestaurantMapper.mapToDto(order.getRestaurantId().getAddress()));
        List<ItemsDTO> items = new ArrayList<>();
        order.getItems().forEach(item -> items.add(new ItemsDTO(item.getPrice(),item.getQuantity(), item.getRestaurantMenuItem()
                .getDescription(), item.getRestaurantMenuItem().getImage())));
        orderDTO.setItems(items);

        return orderDTO;
    }


    @Operation(summary = "Создать новый заказ")
    public OrderConfirmationDTO createOrder(OrderCreationDTO orderCreationDto) {
        return new OrderConfirmationDTO(1L, "https://secretPaymentUrl.com", Date.from(Instant.now().plusSeconds(100)));
    }
}
