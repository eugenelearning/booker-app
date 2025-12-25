package io.booker.hotels.models;

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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(updatable = false)
    UUID hotel_id;

    String number;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private RoomState state;
}
