INSERT INTO hotels (id, name, address) VALUES (RANDOM_UUID(),'Mercure Moscow Baumanskaya', 'Baumanskaya 54 bld.1, Moscow 105005 Russia');
INSERT INTO hotels (id, name, address) VALUES (RANDOM_UUID(),'Mamaison All Suites Spa Hotel Pokrovka', 'Pokrovka St., 40/2, Moscow 101000 Russia');
INSERT INTO hotels (id, name, address) VALUES (RANDOM_UUID(),'Raikin Plaza Hotel', 'Building 1 ul. Sheremetevskaya 6, Moscow 129594 Russia');

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '101', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 3, (select id from rooms where number = '101'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '102', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 1, (select id from rooms where number = '102'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '103', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 6, (select id from rooms where number = '103'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '104', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 0, (select id from rooms where number = '104'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '201', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 1, (select id from rooms where number = '201'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '202', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 0, (select id from rooms where number = '202'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '203', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 2, (select id from rooms where number = '203'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '204', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 4, (select id from rooms where number = '204'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '505', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 4, (select id from rooms where number = '505'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '506', (select id from hotels where name = 'Mercure Moscow Baumanskaya'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 4, (select id from rooms where number = '506'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'A1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 4, (select id from rooms where number = 'A1'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'A2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 2, (select id from rooms where number = 'A2'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'A3', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 6, (select id from rooms where number = 'A3'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'A4', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 1, (select id from rooms where number = 'A4'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'B1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 1, (select id from rooms where number = 'B1'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'B2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 4, (select id from rooms where number = 'B2'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'B3', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 0, (select id from rooms where number = 'B3'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'B4', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 0, (select id from rooms where number = 'B4'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'D1', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 17, (select id from rooms where number = 'D1'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), 'D2', (select id from hotels where name = 'Mamaison All Suites Spa Hotel Pokrovka'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 15, (select id from rooms where number = 'D2'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '11', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 3, (select id from rooms where number = '11'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '12', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 2, (select id from rooms where number = '12'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '13', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 1, (select id from rooms where number = '13'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '14', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 2, (select id from rooms where number = '14'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '15', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 3, (select id from rooms where number = '15'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '21', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 4, (select id from rooms where number = '21'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '22', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 5, (select id from rooms where number = '22'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '23', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 6, (select id from rooms where number = '23'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '24', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 7, (select id from rooms where number = '24'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '25', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), true, 4, (select id from rooms where number = '25'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '31', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 24, (select id from rooms where number = '31'));

INSERT INTO rooms (id, number, hotel_id) values (RANDOM_UUID(), '32', (select id from hotels where name = 'Raikin Plaza Hotel'));
INSERT INTO rooms_states (id, available, times_booked, room_id) values (RANDOM_UUID(), false, 19, (select id from rooms where number = '32'));