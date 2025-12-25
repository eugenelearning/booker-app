package io.booker.hotels.repositories;

import io.booker.hotels.models.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelsRepository extends CrudRepository<Hotel, UUID> {
    @Query("SELECT h FROM Hotel h JOIN FETCH h.rooms r JOIN FETCH r.state s WHERE s.available = true")
    List<Hotel> findAllWithRooms();
}
