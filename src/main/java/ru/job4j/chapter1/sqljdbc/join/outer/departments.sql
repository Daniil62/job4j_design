create table departments(
	id serial primary key,
	name varchar(255)
);

create table emploers(
	id serial primary key,
	name varchar(255),
	dep_id int references departments(id)
);

insert into departments(name)
values('dep.1'), ('dep.2'), ('dep.3'), ('dep.4'), ('dep.5');

insert into emploers(name, dep_id)
values('Ivan', 1), ('Boris', 2), ('Vladislav', 4);

select * from emploers e left join departments d on e.dep_id = d.id;
select * from emploers e right join departments d on e.dep_id = d.id;
select * from emploers e full join departments d on e.dep_id = d.id;
select * from emploers cross join departments;

select * from departments d left join emploers e 
on d.id = e.dep_id where e is null;

select e.name, d.name from emploers e
left join departments d on e.dep_id = d.id;
select e.name, d.name from departments d
right join emploers e on e.dep_id = d.id;

select d.name, e.name from departments d
left join emploers e on e.dep_id = d.id;
select d.name, e.name from emploers e
right join departments d on e.dep_id = d.id;