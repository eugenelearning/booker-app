package io.booker.hotels.services;

import io.booker.hotels.clients.BookingClient;
import io.booker.hotels.dto.RoomDTO;
import io.booker.hotels.models.Room;
import io.booker.hotels.models.RoomState;
import io.booker.hotels.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private BookingClient bookingClient;

    public Optional<Room> getById(UUID id) {
        return roomsRepository.findById(id);
    }

    public void deleteById(UUID id) {
        roomsRepository.deleteById(id);
    }

    public List<Room> getLeastBooked() {
        var stats = bookingClient.getRoomsStatistics();
        var rooms = roomsRepository.getAllAvailable();

        return rooms.stream()
                .sorted(Comparator.comparing(item -> stats.getOrDefault(item.getId(), 0L)))
                .toList();
    }

    public List<Room> getAllRooms() {
        return roomsRepository.getAllAvailable();
    }

    public Room createRoom(RoomDTO payload) {
        var room = Room.builder()
                .number(payload.getNumber())
                .hotel_id(payload.getHotel_id())
                .build();

        var state = RoomState.builder()
                .available(true)
                .build();

        room.setState(state);
        state.setRoom(room);

        return roomsRepository.save(room);
    }
}
