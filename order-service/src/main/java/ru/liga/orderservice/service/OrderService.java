package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
public class OrderService {

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders() {
        return new GetOrdersResponseDTO(List.of(
                new OrderDTO(1L, new RestaurantDTO("Dodo Pizza"), Date.from(Instant.now()), List.of(new ItemsDTO(980.00, 1, "Big Pizza", "There will be in the future"))),
                new OrderDTO(2L, new RestaurantDTO("SPAR"), Date.from(Instant.now()), List.of(new ItemsDTO(100.00, 2, "Something", "There will be in the future")))),
                1,
                10
        );
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(Long id) {
        return new OrderDTO(id, new RestaurantDTO("Dodo Pizza"), Date.from(Instant.now()), List.of(new ItemsDTO(980.00, 1, "Big Pizza", "There will be in the future")));
    }

    @Operation(summary = "Создать новый заказ")
    public OrderConfirmationDTO createOrder(OrderCreationDTO orderCreationDto) {
        return new OrderConfirmationDTO(1L, "https://secretPaymentUrl.com", Date.from(Instant.now().plusSeconds(100)));
    }
}
