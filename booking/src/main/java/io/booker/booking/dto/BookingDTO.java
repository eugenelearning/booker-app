package io.booker.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    UUID user_id;
    UUID room_id;
    LocalDate date_from;
    LocalDate date_to;
}
