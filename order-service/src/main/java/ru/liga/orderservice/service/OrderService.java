package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@Schema(description = "Сервис для оформления заказов")
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
public class OrderService {


    private final OrderRepository orderRepository;
    private final AbstractMapper<Order, OrderDTO> orderMapper;
    private final AbstractMapper<OrderItem, ItemsDTO> itemsMapper;

    @Operation(summary = "Получить все заказы")
    public GetResponseDTO<OrderDTO> getAllOrders(PageRequest pageRequest) {
        Page<Order> orders = orderRepository.findAll(pageRequest);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {

            OrderDTO orderDTO = orderMapper.toDto(order);
            List<ItemsDTO> items =itemsMapper.toDto(order.getItems());

            orderDTO.setItems(items);
            orderDTOList.add(orderDTO);
        }
        return new GetResponseDTO<>(orderDTOList, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.getOrderById(id).orElseThrow();

        OrderDTO orderDTO = orderMapper.toDto(order);
        List<ItemsDTO> items =itemsMapper.toDto(order.getItems());

        orderDTO.setItems(items);

        return orderDTO;
    }


    @Operation(summary = "Создать новый заказ")
    public OrderConfirmationDTO createOrder(OrderCreationDTO orderCreationDto) {
        return new OrderConfirmationDTO(1L, "https://secretPaymentUrl.com", Date.from(Instant.now().plusSeconds(100)));
    }
}
