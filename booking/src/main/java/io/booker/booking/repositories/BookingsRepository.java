package io.booker.booking.repositories;

import io.booker.booking.models.Booking;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingsRepository extends CrudRepository<Booking, UUID> {
    @Query("SELECT b FROM Booking b WHERE b.room_id = :room_id " +
            "AND b.state <> 'CANCELLED'" +
            "AND (:date_from BETWEEN b.date_from AND b.date_to " +
            "OR :date_to BETWEEN b.date_from AND b.date_to)")
    List<Booking> getOccupiedSlots(
            @Param("room_id") UUID roomId,
            @Param("date_from") LocalDate dateFrom,
            @Param("date_to") LocalDate dateTo
    );

    @Query("SELECT b.room_id, COUNT(b.id) FROM Booking b GROUP BY b.room_id")
    List<Tuple> getRoomStats();
}
