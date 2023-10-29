package ru.liga.kitchenservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.ActionDTO;


/**
 * Feign клиент для общения с delivery-service
 */
@FeignClient(name = "kitchen-client", url = "http://localhost:8080")
public interface KitchenClient {

    /**
     * Обновить статус заказа
     */
    @PostMapping("/deliveries/{id}")
    ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody ActionDTO actionDTO);
}