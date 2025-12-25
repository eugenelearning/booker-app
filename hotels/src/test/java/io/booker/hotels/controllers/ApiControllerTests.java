package io.booker.hotels.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.booker.hotels.dto.HotelDTO;
import io.booker.hotels.dto.RoomDTO;
import io.booker.hotels.services.HotelsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    HotelsService service;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void findAllShouldReturnAllHotels() throws Exception {
        var items = service.getAllWithRooms();

        mvc.perform(
                        get("/api/hotels")
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read")
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(items.size()));
    }

    @Test
    void findByIdShouldReturnAHotel() throws Exception {
        var items = service.getAllWithRooms();
        var item = items.getFirst();

        mvc.perform(
                        get("/api/hotels/" + item.getId())
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read")
                                        )
                                )
                )
                .andExpect(jsonPath("$.name").value(item.getName()));
    }

    @Test
    void findAllShouldDropErrorOnInvalidToken() throws Exception {
        mvc.perform(
                        get("/api/hotels")
                                .with(SecurityMockMvcRequestPostProcessors.jwt())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void createHotelShouldDropErrorOnInvalidToken() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var hotelName = "createdHotel";

        mvc.perform(
                        post("/api/hotels")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(HotelDTO
                                        .builder()
                                        .name(hotelName)
                                        .address("test")
                                        .build()
                                ))
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read")
                                        )
                                )
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void createHotelShouldReturnHotel() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        var hotelName = "createdHotel";

        mvc.perform(
                        post("/api/hotels")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(HotelDTO
                                        .builder()
                                        .name(hotelName)
                                        .address("test")
                                        .build()
                                ))
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.write")
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(hotelName));
    }

    @Test
    void createHotelRoomShouldReturnRoom() throws Exception {
        var items = service.getAllWithRooms();
        var item = items.get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        var roomNumber = "B123";

        mvc.perform(
                        post("/api/hotels/" + item.getId() + "/rooms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(RoomDTO
                                        .builder()
                                        .available(true)
                                        .number(roomNumber)
                                        .build()
                                ))
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.write")
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(roomNumber));
    }

    @Test
    void deleteHotelByIdShouldReturnOk() throws Exception {
        var items = service.getAllWithRooms();
        var item = items.get(0);

        mvc.perform(
                delete("/api/hotels/" + item.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_api.write")
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void deleteByIdDropErrorOnInvalidToken() throws Exception {
        var items = service.getAllWithRooms();
        var item = items.get(0);

        mvc.perform(
                delete("/api/hotels/" + item.getId())
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_api.read")
                                )
                        )
        ).andExpect(status().isForbidden());
    }

    @Test
    void deleteHotelRoomShouldReturnOk() throws Exception {
        var items = service.getAllWithRooms();
        var item = items.get(0);
        var roomId = item.getRooms().get(0).getId();

        mvc.perform(
                        delete("/api/hotels/" + item.getId() + "/rooms/" + roomId)
                                .with(SecurityMockMvcRequestPostProcessors.jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.write")
                                        )
                                )
                )
                .andExpect(status().isOk());
    }
}



