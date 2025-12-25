CREATE TABLE IF NOT EXISTS bookings (
    id        uuid default RANDOM_UUID() PRIMARY KEY,
    state	  ENUM('PENDING', 'CONFIRMED', 'CANCELLED') default 'PENDING',
    user_id   uuid not null,
    room_id   uuid not null,
    date_from timestamp NOT NULL,
    date_to   timestamp NOT NULL,
    created   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);