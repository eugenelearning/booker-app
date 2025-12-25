package io.booker.booking.services;

import io.booker.booking.dto.BookingDTO;
import io.booker.booking.models.Booking;
import io.booker.booking.models.BookingState;
import io.booker.booking.repositories.BookingsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingsRepository bookingsRepository;

    public boolean isRoomSlotVacant(UUID roomId, LocalDate dateFrom, LocalDate dateTo) {
        var occupied = bookingsRepository.getOccupiedSlots(roomId, dateFrom, dateTo);

        return occupied.isEmpty();
    }

    public List<Booking> getAllBookings() {
        var result = new ArrayList<Booking>();

        for (Booking item : bookingsRepository.findAll()) {
            result.add(item);
        }

        return result;
    }

    public Optional<Booking> getById(UUID bookingId) {
        return bookingsRepository.findById(bookingId);
    }

    public Map<UUID, Long> getRoomStatistics() {
        return bookingsRepository.getRoomStats().stream().collect(
                Collectors.toMap(
                        tuple -> (UUID) tuple.get(0),
                        tuple -> (Long) tuple.get(1)
                )
        );
    }

    @Transactional
    public void setBookingState(UUID id, BookingState state) {
        var result = bookingsRepository.findById(id);

        if (result.isPresent()) {
            var booking = result.get();

            if (booking.getState() == BookingState.PENDING) {
                booking.setState(state);

                bookingsRepository.save(booking);
            }
        }
    }

    @Transactional
    public Booking createBooking(BookingDTO payload) {
        if (isRoomSlotVacant(payload.getRoom_id(), payload.getDate_from(), payload.getDate_to())) {
            return bookingsRepository.save(
                    Booking.builder()
                            .room_id(payload.getRoom_id())
                            .user_id(payload.getUser_id())
                            .date_from(payload.getDate_from())
                            .date_to(payload.getDate_to())
                            .build()
            );
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT, "Room time slot is occupied");
    }
}
