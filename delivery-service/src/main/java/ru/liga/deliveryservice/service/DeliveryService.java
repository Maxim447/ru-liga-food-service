package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.deliveryservice.dto.*;

import java.util.List;


@Schema(description = "Сервис для отправки заказов курьерам")
@Service
public class DeliveryService {

    @Operation(summary = "Получить все доставки")
    public GetDeliveriesResponseDTO getDeliveries(String status) {
        return new GetDeliveriesResponseDTO(List.of(
                new DeliveryDTO(1L, new RestaurantDTO("Moskovskay", 1000.0), new CustomerDTO("Nizhnia", 500.0), "card"),
                new DeliveryDTO(2L, new RestaurantDTO("Nizhnia", 150.0), new CustomerDTO("Moskovskay", 2300.0), "card")),
                1,
                10
        );
    }

    @Operation(summary = "Создать доставку")
    public ResponseEntity<?> createDelivery(Long id, ActionDTO actionDTO) {
        switch (actionDTO.getOrderAction()) {
            case "accept":
            case "completed":
                return new ResponseEntity<>(HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
