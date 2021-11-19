create table vehicle(
	id serial primary key,
	mark varchar(255),
	model varchar(255),
	color varchar(255),
	vin text,
	year int
);
create table license_plate(
	id serial primary key,
	number text,
	region int
);
create table registration(
	id serial primary key,
	vehicle_id int references vehicle(id) unique,
	license_plate_id int references license_plate(id) unique
);
insert into vehicle(mark, model, color, vin, year) values('Lada', '2121 Bronto', 'Black', '*****************', 2021);
insert into license_plate(number, region) values('X001XX', 62);
insert into registration(vehicle_id, license_plate_id) values(1, 1);