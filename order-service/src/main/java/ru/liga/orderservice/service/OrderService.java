package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.liga.dto.*;
import ru.liga.entity.Order;
import ru.liga.orderservice.mapper.OrderMapper;
import ru.liga.orderservice.mapper.RestaurantMapper;
import ru.liga.orderservice.repository.OrderRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@RequiredArgsConstructor
@Service
public class OrderService {


    private final OrderRepository orderRepository;

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getAllOrders(PageRequest pageRequest) {
        Page<Order> orders = orderRepository.findAll(pageRequest);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            List<ItemsDTO> items = new ArrayList<>();
            OrderDTO orderDTO = OrderMapper.mapToDto(order, RestaurantMapper.mapToDto(order.getRestaurantId()
                    .getName()));
            order.getItems()
                    .forEach(item -> items.add(new ItemsDTO(item.getPrice().doubleValue(), item.getQuantity(), item.getRestaurantMenuItem()
                            .getDescription(), item.getRestaurantMenuItem().getImage())));
            orderDTO.setItems(items);
            orderDTOList.add(orderDTO);
        }
        return new GetOrdersResponseDTO(orderDTOList, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.getOrderById(id).orElseThrow();
        OrderDTO orderDTO = OrderMapper.mapToDto(order, RestaurantMapper.mapToDto(order.getRestaurantId()
                .getName()));
        List<ItemsDTO> items = new ArrayList<>();
        order.getItems()
                .forEach(item -> items.add(new ItemsDTO(item.getPrice().doubleValue(), item.getQuantity(), item.getRestaurantMenuItem()
                        .getDescription(), item.getRestaurantMenuItem().getImage())));
        orderDTO.setItems(items);

        return orderDTO;
    }


    @Operation(summary = "Создать новый заказ")
    public OrderConfirmationDTO createOrder(OrderCreationDTO orderCreationDto) {
        return new OrderConfirmationDTO(1L, "https://secretPaymentUrl.com", Date.from(Instant.now().plusSeconds(100)));
    }
}
