package io.booker.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    UUID user_id;
    UUID room_id;

    LocalDate date_from;
    LocalDate date_to;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, columnDefinition = "state")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    BookingState state;

    @Column(updatable = false, insertable = false)
    LocalDate created;
}
