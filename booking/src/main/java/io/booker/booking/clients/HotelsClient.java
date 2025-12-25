package io.booker.booking.clients;

import io.booker.booking.models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "hotels")
public interface HotelsClient {
    @GetMapping("/api/hotels")
    List<Hotel> getHotels();
}


