package io.booker.booking.controllers;

import com.netflix.discovery.EurekaClient;
import io.booker.booking.dto.BookingDTO;
import io.booker.booking.dto.BookingInputDTO;
import io.booker.booking.models.Booking;
import io.booker.booking.models.BookingState;
import io.booker.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ApiController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/statistics/rooms")
    public Map<UUID, Long> getRoomStatistics() {
        return bookingService.getRoomStatistics();
    }

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/bookings/{id}")
    public Optional<Booking> getBookingById(@PathVariable("id") UUID bookingId) {
        return bookingService.getById(bookingId);
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @PostMapping("/api/bookings")
    public Booking createBooking(
            @AuthenticationPrincipal Jwt principal,
            @RequestBody BookingInputDTO payload
    ) {
        return bookingService.createBooking(
                BookingDTO.builder()
                        .user_id(UUID.fromString(principal.getClaim("user_id")))
                        .room_id(UUID.fromString(payload.getRoom_id()))
                        .date_from(payload.getDate_from())
                        .date_to(payload.getDate_to())
                        .build()
        );
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @PostMapping("/api/bookings/{id}/cancel")
    public void cancelBooking(@PathVariable("id") UUID id) {
        bookingService.setBookingState(id, BookingState.CANCELLED);
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @PostMapping("/api/bookings/{id}/confirm")
    public void confirmBooking(@PathVariable("id") UUID id) {
        bookingService.setBookingState(id, BookingState.CONFIRMED);
    }
}
