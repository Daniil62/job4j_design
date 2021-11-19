create table constellation(
	id serial primary key,
	name varchar(255)
);
create table stars(
	id serial primary key,
	name varchar(255),
	constellation_id int references constellation(id)
);
insert into constellation(name) values('Cassiopeia');
insert into stars(name, constellation_id) values('Segin', 1);
insert into stars(name, constellation_id) values('Ruchbah', 1);
insert into stars(name, constellation_id) values('Navi', 1);
select * from stars;
select * from constellation where id in (select id from stars);