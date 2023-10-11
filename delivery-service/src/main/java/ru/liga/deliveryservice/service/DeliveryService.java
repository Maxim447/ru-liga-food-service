package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.deliveryservice.dto.ActionDTO;
import ru.liga.deliveryservice.dto.DeliveryDTO;
import ru.liga.deliveryservice.model.Customer;
import ru.liga.deliveryservice.model.Restaurant;

import java.util.List;


@Schema(description = "Сервис для отправки заказов курьерам")
@Service
public class DeliveryService {

    @Operation(summary = "Получить все доставки")
    public List<DeliveryDTO> getDeliveries(String status) {
        return List.of(
                new DeliveryDTO(1L, new Restaurant("Moskovskay", "1 kilometers"), new Customer("Nizhnia", "500 meters"), "card"),
                new DeliveryDTO(2L, new Restaurant("Nizhnia", "150 meters"), new Customer("Moskovskay", "2.3 kilometers"), "card")
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
