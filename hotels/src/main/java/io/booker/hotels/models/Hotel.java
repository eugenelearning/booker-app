package io.booker.hotels.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;
    String address;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hotel_id", updatable = false)
    private List<Room> rooms;
}
