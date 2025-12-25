package io.booker.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    String number;
    UUID hotel_id;
    Boolean available;
}