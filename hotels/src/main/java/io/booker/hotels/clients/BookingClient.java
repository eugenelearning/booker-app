package io.booker.hotels.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.UUID;

@FeignClient(
        name = "booking"
)
public interface BookingClient {
    @GetMapping("/api/statistics/rooms")
    Map<UUID, Long> getRoomsStatistics();
}

