package ru.liga.kitchenservice.mapper;

import lombok.experimental.UtilityClass;
import ru.liga.dto.MenuItemsDTO;
import ru.liga.dto.KitchenOrderDTO;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OrderMapper {

    public static List<KitchenOrderDTO> mapToDto(List<Order> orders) {
        List<KitchenOrderDTO> orderDTOList = new ArrayList<>();
        List<MenuItemsDTO> menuItemsDTOList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getItems();
            for (OrderItem orderItem : orderItems) {
                menuItemsDTOList.add(new MenuItemsDTO(orderItem.getQuantity(), orderItem
                        .getRestaurantMenuItem().getId()));
            }
            orderDTOList.add(new KitchenOrderDTO(order.getId(), menuItemsDTOList));
        }
        return orderDTOList;
    }
}
