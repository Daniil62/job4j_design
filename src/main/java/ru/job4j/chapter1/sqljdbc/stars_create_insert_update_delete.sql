create table stars(
	id serial primary key,
	name varchar(255),
	constellation text
);
insert into stars(name, constellation) values('Algol', 'Perseus');
update stars set name = 'Mirfak';
delete from stars;