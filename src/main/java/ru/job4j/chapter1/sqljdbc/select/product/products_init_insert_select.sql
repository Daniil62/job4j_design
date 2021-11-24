create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values('meat products'), ('groats'), ('dairy'), ('cheese'), ('ice cream');

insert into product(name, type_id, expired_date, price) values('chicken fillet', 1, '30.11.2021', 250.0);
insert into product(name, type_id, expired_date, price) values('turkey fillet', 1, '30.11.2021', 300.0);
insert into product(name, type_id, expired_date, price) values('buckwheat', 2, '30.12.2021', 80.50);
insert into product(name, type_id, expired_date, price) values('oatmeal', 2, '04.01.2022', 50.99);
insert into product(name, type_id, expired_date, price) values('curd 1%', 3, '29.11.2021', 80.0);
insert into product(name, type_id, expired_date, price) values('sour cream', 3, '27.11.2021', 80.0);
insert into product(name, type_id, expired_date, price) values('parmesan', 4, '30.11.2021', 1600.0);
insert into product(name, type_id, expired_date, price) values('mascarpone', 4, '25.11.2021', 1500.0);
insert into product(name, type_id, expired_date, price) values('chocolate ice cream', 5, '20.11.2021', 100.0);

select p.name, p.expired_date, p.price
from product p join type t
on t.name = 'cheese' and p.type_id = t.id;

select name, expired_date, price
from product where name like '% ice cream %';

select name from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(p.id)
from type t join product p on t.id = p.type_id
group by t.id;

select p.name, p.expired_date, p.price
from product p join type t on (t.name = 'dairy' or t.name = 'cheese')
and p.type_id = t.id;

select t.name, count(p.id)
from type t join product p on t.id = p.type_id
group by t.id
having count(p.id) < 2;

select p.name, t.name from product p join type t on p.type_id = t.id;