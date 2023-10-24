package ru.liga.deliveryservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.dto.FullRestaurantDTO;
import ru.liga.dto.GetResponseDTO;


@FeignClient(name = "kitchen-client", url = "http://localhost:8081")
public interface KitchenClient {

    @GetMapping("/restaurant/")
    GetResponseDTO<FullRestaurantDTO> getRestaurants(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageCount") Integer pageCount);
}
