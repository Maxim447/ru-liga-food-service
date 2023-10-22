package ru.liga.deliveryservice.mapper;

import ru.liga.dto.CustomerDTO;
import ru.liga.dto.DeliveryDTO;
import ru.liga.dto.DeliveryRestaurantDTO;
import ru.liga.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {

    public static List<DeliveryDTO> mapToDto(List<Order> orders) {
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>();
        for (Order order : orders) {
            deliveryDTOS.add(new DeliveryDTO(order.getId(),
                    new DeliveryRestaurantDTO(order.getRestaurantId().getAddress(), 150.0),
                    new CustomerDTO(order.getCustomerId().getAddress(), 150.0),
                    150.0));
        }
        return deliveryDTOS;
    }
}
