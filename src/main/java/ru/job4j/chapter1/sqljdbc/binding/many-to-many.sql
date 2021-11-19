create table users(
	id serial primary key,
	name varchar(255)
);
create table groups(
	id serial primary key,
	name varchar(255)
);
create table users_groups(
	id serial primary key,
	user_id int references users(id),
	group_id int references groups(id)
);
insert into users(name) values('Kirill');
insert into users(name) values('Klavdia');
insert into users(name) values('Klim');

insert into groups(name) values('First');
insert into groups(name) values('Second');
insert into groups(name) values('Third');

insert into users_groups(user_id, group_id) values(1, 1);
insert into users_groups(user_id, group_id) values(1, 2);
insert into users_groups(user_id, group_id) values(2, 1);
insert into users_groups(user_id, group_id) values(2, 2);
insert into users_groups(user_id, group_id) values(2, 3);
insert into users_groups(user_id, group_id) values(3, 3);