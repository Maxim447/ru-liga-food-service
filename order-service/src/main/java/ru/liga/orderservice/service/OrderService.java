package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;
import ru.liga.orderservice.dto.OrderConfirmationDto;
import ru.liga.orderservice.dto.OrderCreationDto;
import ru.liga.orderservice.dto.OrderDTO;
import ru.liga.orderservice.model.Items;
import ru.liga.orderservice.model.Restaurant;

import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
public class OrderService {

    @Operation(summary = "Получить все заказы")
    public List<OrderDTO> getOrders() {
        return List.of(
                new OrderDTO(1L, new Restaurant("Dodo Pizza"), List.of(new Items(980.00, 1, "Big Pizza", "There will be in the future"))),
                new OrderDTO(2L, new Restaurant("SPAR"), List.of(new Items(100.00, 2, "Something", "There will be in the future")))
        );
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(Long id) {
        return new OrderDTO(id, new Restaurant("Dodo Pizza"), List.of(new Items(980.00, 1, "Big Pizza", "There will be in the future")));
    }

    @Operation(summary = "Создать новый заказ")
    public OrderConfirmationDto createOrder(OrderCreationDto orderCreationDto) {
        return new OrderConfirmationDto(1L, "https://secretPaymentUrl.com", "12:00");
    }
}
