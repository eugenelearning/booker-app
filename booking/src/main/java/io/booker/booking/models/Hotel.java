package io.booker.booking.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Hotel {
    UUID id;
    String name;
    String address;
}
