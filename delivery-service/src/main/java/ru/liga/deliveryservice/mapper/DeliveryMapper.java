package ru.liga.deliveryservice.mapper;

import ru.liga.deliveryservice.dto.CustomerDTO;
import ru.liga.deliveryservice.dto.DeliveryDTO;
import ru.liga.deliveryservice.dto.RestaurantDTO;
import ru.liga.deliveryservice.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {

    public static List<DeliveryDTO> mapToDto(List<Order> orders) {
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>();
        for (Order order : orders) {
            deliveryDTOS.add(new DeliveryDTO(order.getId(),
                    new RestaurantDTO(order.getRestaurantId().getAddress(), 150.0),
                    new CustomerDTO(order.getCustomerId().getAddress(), 150.0),
                    "card"));
        }
        return deliveryDTOS;
    }
}
