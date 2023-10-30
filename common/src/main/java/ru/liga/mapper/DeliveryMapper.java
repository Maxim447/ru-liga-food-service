package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.liga.dto.DeliveryDTO;
import ru.liga.entity.Order;
import ru.liga.mapper.abstraction.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper extends AbstractMapper<Order, DeliveryDTO> {
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "restaurantId.address", target = "restaurant.address")
    @Mapping(source = "customerId.address", target = "customer.address")
    DeliveryDTO toDto(Order order);

    List<DeliveryDTO> toDto(List<Order> orders);

    @Mapping(source = "orderId", target = "id")
    @Mapping(source = "restaurant.address", target = "restaurantId.address")
    @Mapping(source = "customer.address", target = "customerId.address")
    Order toEntity(DeliveryDTO dto);
}
