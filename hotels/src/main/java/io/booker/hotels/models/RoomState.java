package io.booker.hotels.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms_states")
public class RoomState {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    Boolean available;
    Integer times_booked;

    @OneToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private Room room;
}
