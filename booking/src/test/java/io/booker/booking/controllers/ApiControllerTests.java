package io.booker.booking.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.booker.booking.dto.BookingDTO;
import io.booker.booking.dto.BookingInputDTO;
import io.booker.booking.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    BookingService service;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    private Jwt getToken() {
        var userId = UUID.randomUUID();

        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "RS256");

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userId.toString());

        return new Jwt("tokenValue", Instant.now(), Instant.now().plusSeconds(3600), headers, claims);
    }


    @Test
    void findAllShouldReturnAllBookings() throws Exception {
        var items = service.getAllBookings();

        mvc.perform(
                        get("/api/bookings")
                                .with(jwt()
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read")
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(items.size()));
    }


    @Test
    void findAllShouldDropErrorOnInvalidToken() throws Exception {
        mvc.perform(
                        get("/api/bookings")
                                .with(jwt())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void createBookingShouldDropErrorOnInvalidToken() throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        var userId = UUID.randomUUID();
        var roomId = UUID.randomUUID();

        mvc.perform(
                        post("/api/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(BookingDTO.builder()
                                        .user_id(userId)
                                        .room_id(roomId)
                                        .date_from(LocalDate.parse("2025-11-10"))
                                        .date_to(LocalDate.parse("2025-11-20"))
                                        .build()
                                ))

                                .with(jwt()

                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read")
                                        )
                                )
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void createShouldReturnBooking() throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        var roomId = UUID.randomUUID();

        mvc.perform(
                        post("/api/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(BookingInputDTO.builder()
                                        .room_id(roomId.toString())
                                        .date_from(LocalDate.parse("2025-11-10"))
                                        .date_to(LocalDate.parse("2025-11-20"))
                                        .build()
                                ))
                                .with(jwt()
                                        .jwt(getToken())
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read"),
                                                new SimpleGrantedAuthority("ROLE_api.write")
                                        )
                                )
                )
                .andExpect(status().isOk());
    }

    @Test
    void createShouldDropErrorOnConflict() throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        mvc.perform(
                        post("/api/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(BookingInputDTO.builder()
                                        .room_id("b428fa93-d7bf-4df4-a6e4-42a876fb0920")
                                        .date_from(LocalDate.parse("2024-02-10"))
                                        .date_to(LocalDate.parse("2024-02-20"))
                                        .build()
                                ))
                                .with(jwt()
                                        .jwt(getToken())
                                        .authorities(
                                                new SimpleGrantedAuthority("ROLE_api.read"),
                                                new SimpleGrantedAuthority("ROLE_api.write")
                                        )
                                )
                )
                .andExpect(status().isConflict());
    }

    @Test
    void confirmShouldChangeState() throws Exception {
        var bookingId = "b428fa93-d7bf-4df4-a6e4-42a876fb0920";
        var token = jwt()
                .jwt(getToken())
                .authorities(
                        new SimpleGrantedAuthority("ROLE_api.read"),
                        new SimpleGrantedAuthority("ROLE_api.write")
                );

        mvc.perform(
                post("/api/bookings/" + bookingId + "/confirm")
                        .with(token)
        );
        mvc.perform(get("/api/bookings/" + bookingId).with(token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("CONFIRMED"));
    }

    @Test
    void confirmShouldNodChangeCanceledState() throws Exception {
        var bookingId = "b428fa93-d7bf-4df4-a6e4-42a876fb0921";
        var token = jwt()
                .jwt(getToken())
                .authorities(
                        new SimpleGrantedAuthority("ROLE_api.read"),
                        new SimpleGrantedAuthority("ROLE_api.write")
                );

        mvc.perform(
                post("/api/bookings/" + bookingId + "/confirm").with(token)
        );
        mvc.perform(get("/api/bookings/" + bookingId).with(token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("CANCELLED"));
    }

    @Test
    void rejectShouldNodChangeCanceledState() throws Exception {
        var bookingId = "b428fa93-d7bf-4df4-a6e4-42a876fb0920";
        var token = jwt()
                .jwt(getToken())
                .authorities(
                        new SimpleGrantedAuthority("ROLE_api.read"),
                        new SimpleGrantedAuthority("ROLE_api.write")
                );

        mvc.perform(
                post("/api/bookings/" + bookingId + "/cancel")
                        .with(token)
        );
        mvc.perform(get("/api/bookings/" + bookingId)
                        .with(token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("CANCELLED"));
    }
}

