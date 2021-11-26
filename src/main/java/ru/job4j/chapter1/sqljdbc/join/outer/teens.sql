create table teens(
	id serial primary key,
	name varchar(255),
	gender boolean
);

insert into teens(name, gender) values('Maria', false);
insert into teens(name, gender) values('Stepan', true);
insert into teens(name, gender) values('Valentina', false);
insert into teens(name, gender) values('Svyatoslav', true);
insert into teens(name, gender) values('Margarita', false);
insert into teens(name, gender) values('Alexandr', true);

select a.name, b.name from teens a
cross join teens b where a.gender != b.gender;