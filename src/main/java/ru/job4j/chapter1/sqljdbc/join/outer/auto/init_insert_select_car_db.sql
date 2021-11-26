create table body(
	id serial primary key,
	type varchar(255),
	color varchar(255)
);

create table engine(
	id serial primary key,
	type varchar(255),
	capacity float
);

create table gear_box(
	id serial primary key,
	type varchar(255),
	gear_count int
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	box_id int references gear_box(id)
);

insert into body(type, color) values('coupe', 'red');
insert into body(type, color) values('coupe', 'black');
insert into body(type, color) values('universal', 'black');
insert into body(type, color) values('sedan', 'white');
insert into body(type, color) values('pickup', 'green');

insert into engine(type, capacity) values('V8', 2.9);
insert into engine(type, capacity) values('V8', 6.0);
insert into engine(type, capacity) values('V6', 3.5);
insert into engine(type, capacity) values('V6', 5.7);
insert into engine(type, capacity) values('W8', 4.0);
insert into engine(type, capacity) values('Wankel rotary', 1.3);

insert into gear_box(type, gear_count) values('mechanical', 4);
insert into gear_box(type, gear_count) values('mechanical', 5);
insert into gear_box(type, gear_count) values('automatic', 4);
insert into gear_box(type, gear_count) values('automatic', 5);
insert into gear_box(type, gear_count) values('sequental', 6);

insert into car(name, body_id, engine_id, box_id) values('Ferrari F40', 1, 1, 2);
insert into car(name, body_id, engine_id, box_id) values('Chevrolet Camaro rs', 1, 2, 1);
insert into car(name, body_id, engine_id, box_id) values('Chrysler 300C', 3, 3, 4);
insert into car(name, body_id, engine_id, box_id) values('Chrysler 300C', 4, 4, 4);

select c.id, c.name, b.*, e.*, gb.* from car c
left join body b 
on c.body_id = b.id
left join engine e
on c.engine_id = e.id
left join gear_box gb
on c.box_id = gb.id;

select b.id, b.type, b.color from body b left join car c
on b.id = c.body_id where c is null;

select e.id, e.type, e.capacity from engine e left join car c
on e.id = c.engine_id where c is null;

select gb.id, gb.type, gb.gear_count from gear_box gb left join car c
on gb.id = c.box_id where c is null;