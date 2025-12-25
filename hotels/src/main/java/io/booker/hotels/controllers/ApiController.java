package io.booker.hotels.controllers;

import io.booker.hotels.dto.HotelDTO;
import io.booker.hotels.dto.HotelRoomDTO;
import io.booker.hotels.dto.RoomDTO;
import io.booker.hotels.models.Hotel;
import io.booker.hotels.models.Room;
import io.booker.hotels.services.HotelsService;
import io.booker.hotels.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    private HotelsService hotelsService;

    @Autowired
    private RoomsService roomsService;

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/hotels")
    public List<Hotel> getAllHotels() {
        return hotelsService.getAllWithRooms();
    }

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/rooms/recommended")
    public List<Room> getLeastBookedRooms() {
        return roomsService.getLeastBooked();
    }

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/rooms")
    public List<Room> getAllRooms() {
        return roomsService.getAllRooms();
    }

    @PreAuthorize("hasAuthority('ROLE_api.read')")
    @GetMapping("/api/hotels/{hotelId}")
    public Hotel getHotelById(@PathVariable UUID hotelId) {
        var hotel = hotelsService.getById(hotelId);

        if (hotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find hotel");
        }

        return hotel.get();
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @PostMapping("/api/hotels")
    public Hotel createHotel(@RequestBody HotelDTO payload) {
        return hotelsService.createHotel(payload);
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @DeleteMapping("/api/hotels/{hotelId}")
    public void deleteHotel(@PathVariable UUID hotelId) {
        hotelsService.deleteById(hotelId);
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @PostMapping("/api/hotels/{hotelId}/rooms")
    public Room createHotelRoom(
            @PathVariable UUID hotelId,
            @RequestBody HotelRoomDTO payload
    ) {
        var hotel = hotelsService.getById(hotelId);

        if (hotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find hotel");
        }

        return roomsService.createRoom(
                RoomDTO.builder()
                        .hotel_id(hotelId)
                        .available(payload.getAvailable())
                        .number(payload.getNumber())
                        .build()
        );
    }

    @PreAuthorize("hasAuthority('ROLE_api.write')")
    @DeleteMapping("/api/hotels/{hotelId}/rooms/{roomId}")
    public void deleteHotelRoom(
            @PathVariable UUID hotelId,
            @PathVariable UUID roomId
    ) {
        var hotel = hotelsService.getById(hotelId);

        if (hotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find hotel");
        }

        var room = roomsService.getById(roomId);

        if (room.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find room");
        }

        roomsService.deleteById(roomId);
    }
}
