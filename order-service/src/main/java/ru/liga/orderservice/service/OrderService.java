package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.*;
import ru.liga.entity.*;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.entity.enums.RestaurantStatus;
import ru.liga.mapper.OrderItemMapper;
import ru.liga.mapper.OrderMapper;
import ru.liga.orderservice.repository.CustomerRepository;
import ru.liga.orderservice.repository.OrderRepository;
import ru.liga.orderservice.repository.RestaurantMenuItemRepository;
import ru.liga.orderservice.repository.RestaurantRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/**
 * Сервис для работы с заказами
 */
@Schema(description = "Сервис для оформления заказов")
@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = "ru.liga.mapper")
@Transactional
public class OrderService {


    /**
     * Репозиторий для работы с базой днаыых orders
     */
    private final OrderRepository orderRepository;

    /**
     * Репозиторий для работы с базой днаыых restaurants
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Репозиторий для работы с базой днаыых customers
     */
    private final CustomerRepository customerRepository;

    /**
     * Репозиторий для работы с базой днаыых restaurant_menu_items
     */
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;

    /**
     * Маппер для преобразования сущности Order в OrderDTO
     */
    private final OrderMapper orderMapper;

    /**
     * Маппер для преобразования сущности OrderItem в ItemsDTO
     */
    private final OrderItemMapper itemsMapper;

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
    public ResponseEntity<?> getOrderById(UUID id) {
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
    public ResponseEntity<?> createOrder(OrderCreationDTO orderCreationDto) {
        Restaurant restaurant = restaurantRepository.findById(orderCreationDto.getRestaurantId()).orElseThrow(
                () -> new RuntimeException("Restaurant not found")
        );

        if (restaurant.getStatus() == RestaurantStatus.CLOSED) {
            throw new RuntimeException("Restaurant with id = " + restaurant.getId() + " closed");
        }

        Customer customer = customerRepository.findById(orderCreationDto.getCustomerId()).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );

        Order order = new Order();
        order.setRestaurantId(restaurant);
        order.setStatus(OrderStatus.CUSTOMER_CREATED);
        order.setCustomerId(customer);
        order.setTimestamp(Timestamp.from(Instant.now()));
        List<OrderItem> items = new ArrayList<>();
        for (MenuItemsDTO menuItemDTO : orderCreationDto.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.findById(menuItemDTO.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("menu item not found"));
            OrderItem item = new OrderItem();
            item.setOrderId(order);
            item.setRestaurantMenuItem(restaurantMenuItem);
            item.setQuantity(menuItemDTO.getQuantity());
            item.setPrice(restaurantMenuItem.getPrice());
            items.add(item);
        }
        order.setItems(items);
        UUID orderId = orderRepository.save(order).getId();

        return ResponseEntity.ok(new OrderConfirmationDTO(orderId, "https://secretPaymentUrl.com", Date.from(Instant.now()
                .plusSeconds(100))));
    }

    public ResponseEntity<?> pay(UUID id) {
        Order order = orderRepository.getOrderById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("There is no order with id:" + id)
                );

        order.setStatus(OrderStatus.CUSTOMER_PAID);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> cancel(UUID id) {
        Order order = orderRepository.getOrderById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("There is no order with id:" + id)
                );

        order.setStatus(OrderStatus.CUSTOMER_CANCELLED);
        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }
}
