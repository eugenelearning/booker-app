CREATE TABLE IF NOT EXISTS hotels (
    id       uuid default RANDOM_UUID() NOT NULL PRIMARY KEY,
    name     varchar(100) NOT NULL,
    address  varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS rooms (
    id        uuid default RANDOM_UUID() NOT NULL PRIMARY KEY,
    number    varchar(10) NOT NULL,
    hotel_id  uuid not null references hotels(id)
);

CREATE TABLE IF NOT EXISTS rooms_states (
  id           uuid default RANDOM_UUID() NOT NULL PRIMARY KEY,
  room_id      uuid not null references rooms(id),
  available    boolean default true
);