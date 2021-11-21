create table role(
	id serial primary key,
	name text
);
create table users(
	id serial primary key,
	name text,
	role_id int references role(id)
);
create table rules(
	id serial primary key,
	rule text
);
create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);
create table comments(
	id serial primary key,
	comment varchar(255)
);
create table attachs(
	id serial primary key,
	attach varchar(255)
);
create table item(
	id serial primary key,
	name text,
	user_id int references users(id),
	comment_id int references comments(id),
	attach_id int references attachs(id)
);
create table category(
	id serial primary key,
	name text,
	item_id int references item(id)
);
create table state(
	id serial primary key,
	name text,
	item_id int references item(id)
);