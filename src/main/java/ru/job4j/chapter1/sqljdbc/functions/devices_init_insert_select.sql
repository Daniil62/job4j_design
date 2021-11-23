create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Siemens A50', 500.0);
insert into devices(name, price) values('Apple Watch Series 3', 16400.0);
insert into devices(name, price) values('Samsung Galaxy A50', 17000.0);
insert into devices(name, price) values('Xiaomi Poco X3 Pro 6', 22000.0);
insert into devices(name, price) values('Samsung Galaxy S20 FE', 46000.0);
insert into devices(name, price) values('Samsung Galaxy S21 Ultra 12', 102000.0);

insert into people(name) values('Afrodita'), ('Bronislav'), ('Vasisuali'), ('Alexey');

insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(3, 1);
insert into devices_people(device_id, people_id) values(3, 4);
insert into devices_people(device_id, people_id) values(4, 1);
insert into devices_people(device_id, people_id) values(4, 2);
insert into devices_people(device_id, people_id) values(4, 3);
insert into devices_people(device_id, people_id) values(5, 2);
insert into devices_people(device_id, people_id) values(5, 4);
insert into devices_people(device_id, people_id) values(6, 1);
insert into devices_people(device_id, people_id) values(6, 3);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people as dp join devices as d on d.id = dp.device_id join people p on p.id =
dp.people_id group by p.id;

select p.name, avg(d.price) from devices_people as dp join devices as d on d.id = dp.device_id join people p on p.id =
dp.people_id group by p.id having avg(d.price) > 30000.0;