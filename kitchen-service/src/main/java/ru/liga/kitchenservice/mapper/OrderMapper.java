package ru.liga.kitchenservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.kitchenservice.dto.MenuItemsDTO;
import ru.liga.kitchenservice.dto.OrderDTO;
import ru.liga.kitchenservice.entity.Order;
import ru.liga.kitchenservice.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OrderMapper {

    public static List<OrderDTO> mapToDto(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<MenuItemsDTO> menuItemsDTOList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getItems();
            for (OrderItem orderItem : orderItems) {
                menuItemsDTOList.add(new MenuItemsDTO(orderItem.getQuantity(), orderItem
                        .getRestaurantMenuItem().getId()));
            }
            orderDTOList.add(new OrderDTO(order.getId(), menuItemsDTOList));
        }
        return orderDTOList;
    }
}
