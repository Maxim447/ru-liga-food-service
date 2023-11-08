package ru.liga.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.*;
import ru.liga.entity.*;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.entity.enums.RestaurantStatus;
import ru.liga.mapper.OrderItemMapper;
import ru.liga.mapper.OrderMapper;
import ru.liga.orderservice.rabbitMQ.producer.MessageSender;
import ru.liga.orderservice.repository.*;

import javax.persistence.EntityNotFoundException;
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
@Slf4j
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
     * Отправщик сообщений
     */
    private final MessageSender messageSender;

    private final ObjectMapper objectMapper;

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
        log.info("Создан новый заказ с ID: {}", orderId);
        return ResponseEntity.ok(new OrderConfirmationDTO(orderId, "http://order-service/api/v1/orders/" + orderId + "/pay", Date.from(Instant.now()
                .plusSeconds(100))));
    }

    public ResponseEntity<?> paymentOrder(UUID id) {
        try {
            Order order = orderRepository.getOrderById(id)
                    .orElseThrow(
                            () -> new EntityNotFoundException("There is no order with id:" + id)
                    );
            if (order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.info("Заказ с id=" + id + " уже оплачен.");
                return ResponseEntity.ok("Заказ уже был оплачен");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_CREATED)) {
                log.error("Нельзя оплатить заказ с id=" + id + " в данном состоянии: " + order.getStatus());
                return ResponseEntity.ok("Нельзя оплатить заказ в данном состоянии");
            } else {
                order.setStatus(OrderStatus.CUSTOMER_PAID);
                orderRepository.save(order);
                messageSender.paymentOrder(order);
                log.info("Заказ с id=" + id + " успешно оплачен.");
                return ResponseEntity.ok("Заказ успешно оплачен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ с id=" + id + " не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        }
    }

    public ResponseEntity<?> cancelOrder(UUID id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("There is no order with id:" + id)
            );

            if (order == null) {
                log.error("Заказ с id=" + id + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_DECLINED)) {
                log.info("Заказ с id=" + id + " уже отклонён.");
                return ResponseEntity.ok("Заказ уже был отклонён кухней ранее");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.error("Заказ с id=" + id + " не может быть отклонен, так как его статус не 'CUSTOMER_PAID'.");
                return ResponseEntity.ok("На данный момент нельзя отклонить заказ");
            } else {
                order.setStatus(OrderStatus.KITCHEN_DECLINED);
                orderRepository.save(order);
                messageSender.denyOrder(order);
                log.info("Заказ с id=" + id + " был отклонен.");
                return ResponseEntity.ok("Заказ отклонен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при отклонении заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при отклонении заказа: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> acceptOrder(UUID id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("There is no order with id:" + id)
            );

            if (order == null) {
                log.error("Заказ с id=" + id + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_PREPARING)) {
                log.info("Заказ с id=" + id + " уже принят.");
                return ResponseEntity.ok("Заказ уже был принят кухней ранее");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.error("Заказ с id=" + id + " не может быть принят, так как его статус не 'CUSTOMER_PAID'.");
                return ResponseEntity.ok("На данный момент нельзя принять заказ");
            } else {
                order.setStatus(OrderStatus.KITCHEN_PREPARING);
                orderRepository.save(order);
                messageSender.acceptOrder(order);
                log.info("Заказ с id=" + id + " был успешно принят в работу.");
                return ResponseEntity.ok("Заказ успешно принят в работу");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при принятии заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при принятии заказа: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> completeOrder(UUID id) {
        try {
            Order order = orderRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("There is no order with id:" + id)
            );

            if (order == null) {
                log.error("Заказ с id=" + id + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_FINISHED)) {
                log.info("Заказ с id=" + id + " уже готов.");
                return ResponseEntity.ok("Заказ уже готов, ищем курьера");
            } else if (!order.getStatus().equals(OrderStatus.KITCHEN_PREPARING)) {
                log.error("Заказ с id=" + id + " нельзя сготовить, так как его не приняли.");
                return ResponseEntity.ok("Нельзя сготовить заказ в состоянии отличном от принят");
            } else {
                order.setStatus(OrderStatus.KITCHEN_FINISHED);
                orderRepository.save(order);
                messageSender.completeOrder(order);
                messageSender.sendMessageForDelivery(
                        objectMapper.writeValueAsString(order.getId()),
                        order.getId()
                );
                log.info("Заказ с id=" + id + " был приготовлен.");
                return ResponseEntity.ok("Заказ приготовлен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при приготовлении заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла ошибка при приготовлении заказа: " + ex.getMessage());
        }
    }
}
