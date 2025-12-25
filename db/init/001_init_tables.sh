#!/usr/bin/env bash

set -e

psql -v ON_ERROR_STOP=1 -U "${POSTGRES_USER}" -d hotels <<-EOF
CREATE TYPE booking_state_enum AS ENUM ('PENDING', 'CONFIRMED', 'CANCELLED');

CREATE TABLE hotels (
  id       uuid PRIMARY KEY DEFAULT uuid_generate_v4(), 
  name     varchar(100) NOT NULL,
  address  varchar(250) NOT NULL
);

CREATE TABLE rooms (
  id        uuid PRIMARY KEY DEFAULT uuid_generate_v4(), 
  number    varchar(10) NOT NULL,
  hotel_id  uuid not null references hotels(id)
);

CREATE TABLE rooms_states (
  id           uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  room_id      uuid not null references rooms(id),
  available    boolean default true,
  times_booked integer CHECK (times_booked >= 0) DEFAULT 0
);

CREATE TABLE bookings (
  id        uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id   uuid not null,
  room_id   uuid not null,
  date_from timestamp NOT NULL,
  date_to   timestamp NOT NULL,
  created   timestamp NOT NULL default current_timestamp
);
EOF