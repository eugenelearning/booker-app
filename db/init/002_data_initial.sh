#!/usr/bin/env bash

set -e

psql -v ON_ERROR_STOP=1 -U "${POSTGRES_USER}" -d "${POSTGRES_DB}" <<-EOF
INSERT INTO hotels (name, address) VALUES ('Mercure Moscow Baumanskaya', 'Baumanskaya 54 bld.1, Moscow 105005 Russia');
INSERT INTO hotels (name, address) VALUES ('Mamaison All Suites Spa Hotel Pokrovka', 'Pokrovka St., 40/2, Moscow 101000 Russia');
INSERT INTO hotels (name, address) VALUES ('Raikin Plaza Hotel', 'Building 1 ul. Sheremetevskaya 6, Moscow 129594 Russia');

INSERT INTO rooms (number, hotel_id) values ('101', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 3, (select id from rooms where number = '101'));

INSERT INTO rooms (number, hotel_id) values ('102', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 1, (select id from rooms where number = '102'));

INSERT INTO rooms (number, hotel_id) values ('103', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 6, (select id from rooms where number = '103'));

INSERT INTO rooms (number, hotel_id) values ('104', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 0, (select id from rooms where number = '104'));

INSERT INTO rooms (number, hotel_id) values ('201', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 1, (select id from rooms where number = '201'));

INSERT INTO rooms (number, hotel_id) values ('202', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 0, (select id from rooms where number = '202'));

INSERT INTO rooms (number, hotel_id) values ('203', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 2, (select id from rooms where number = '203'));

INSERT INTO rooms (number, hotel_id) values ('204', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 4, (select id from rooms where number = '204'));

INSERT INTO rooms (number, hotel_id) values ('505', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 4, (select id from rooms where number = '505'));

INSERT INTO rooms (number, hotel_id) values ('506', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 4, (select id from rooms where number = '506'));


INSERT INTO rooms (number, hotel_id) values ('A1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 4, (select id from rooms where number = 'A1'));


INSERT INTO rooms (number, hotel_id) values ('A2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 2, (select id from rooms where number = 'A2'));

INSERT INTO rooms (number, hotel_id) values ('A3', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 6, (select id from rooms where number = 'A3'));

INSERT INTO rooms (number, hotel_id) values ('A4', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 1, (select id from rooms where number = 'A4'));

INSERT INTO rooms (number, hotel_id) values ('B1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 1, (select id from rooms where number = 'B1'));

INSERT INTO rooms (number, hotel_id) values ('B2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 4, (select id from rooms where number = 'B2'));

INSERT INTO rooms (number, hotel_id) values ('B3', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 0, (select id from rooms where number = 'B3'));

INSERT INTO rooms (number, hotel_id) values ('B4', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 0, (select id from rooms where number = 'B4'));

INSERT INTO rooms (number, hotel_id) values ('D1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 17, (select id from rooms where number = 'D1'));

INSERT INTO rooms (number, hotel_id) values ('D2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 15, (select id from rooms where number = 'D2'));

INSERT INTO rooms (number, hotel_id) values ('11', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 3, (select id from rooms where number = '11'));

INSERT INTO rooms (number, hotel_id) values ('12', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 2, (select id from rooms where number = '12'));

INSERT INTO rooms (number, hotel_id) values ('13', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 1, (select id from rooms where number = '13'));

INSERT INTO rooms (number, hotel_id) values ('14', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 2, (select id from rooms where number = '14'));

INSERT INTO rooms (number, hotel_id) values ('15', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 3, (select id from rooms where number = '15'));

INSERT INTO rooms (number, hotel_id) values ('21', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 4, (select id from rooms where number = '21'));

INSERT INTO rooms (number, hotel_id) values ('22', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 5, (select id from rooms where number = '22'));

INSERT INTO rooms (number, hotel_id) values ('23', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 6, (select id from rooms where number = '23'));

INSERT INTO rooms (number, hotel_id) values ('24', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 7, (select id from rooms where number = '24'));

INSERT INTO rooms (number, hotel_id) values ('25', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (true, 4, (select id from rooms where number = '25'));

INSERT INTO rooms (number, hotel_id) values ('31', false, (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 24, (select id from rooms where number = '31'));

INSERT INTO rooms (number, hotel_id) values ('32', false, (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (available, times_booked, room_id) values (false, 19, (select id from rooms where number = '32'));
EOF
