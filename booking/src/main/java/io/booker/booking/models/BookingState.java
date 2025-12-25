package io.booker.booking.models;

public enum BookingState {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    BookingState(String s) {
    }
}
