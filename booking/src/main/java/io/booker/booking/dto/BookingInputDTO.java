package io.booker.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingInputDTO {
    String room_id;
    LocalDate date_from;
    LocalDate date_to;
}
