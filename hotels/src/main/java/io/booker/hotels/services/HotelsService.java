package io.booker.hotels.services;

import io.booker.hotels.dto.HotelDTO;
import io.booker.hotels.models.Hotel;
import io.booker.hotels.repositories.HotelsRepository;
import io.booker.hotels.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelsService {
    @Autowired
    private HotelsRepository hotelsRepository;
    private RoomsRepository roomsRepository;

    public Optional<Hotel> getById(UUID id) {
        return hotelsRepository.findById(id);
    }

    public void deleteById(UUID id) {
        hotelsRepository.deleteById(id);
    }

    public List<Hotel> getAllWithRooms() {
        return hotelsRepository.findAllWithRooms();
    }

    public Hotel createHotel(HotelDTO payload) {
        return hotelsRepository.save(
                Hotel.builder()
                        .name(payload.getName())
                        .address(payload.getAddress())
                        .build()
        );
    }
}
