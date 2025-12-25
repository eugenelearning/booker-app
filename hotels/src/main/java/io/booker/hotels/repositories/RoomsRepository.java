package io.booker.hotels.repositories;

import io.booker.hotels.models.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomsRepository extends CrudRepository<Room, UUID> {
    @Query("SELECT r FROM Room r JOIN FETCH r.state s WHERE s.available = true")
    List<Room> getAllAvailable();
}
