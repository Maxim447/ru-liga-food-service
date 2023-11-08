package ru.liga.kitchenservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;
@FeignClient(name = "OrderFeign", url = "http://localhost:8084/api/v1")
public interface OrderClient {
    @PutMapping("/orders/{id}/cancel")
    String denyOrder(@PathVariable("id") UUID id);

    @PutMapping("/orders/{id}/accept")
    String acceptOrder(@PathVariable("id") UUID id);

    @PutMapping("/orders/{id}/complete")
    String completeOrder(@PathVariable("id") UUID id);

}