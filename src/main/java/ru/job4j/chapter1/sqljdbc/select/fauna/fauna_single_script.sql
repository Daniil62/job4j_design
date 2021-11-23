create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Mexican staring frog of southern Sri Lanka', 10950, '10.06.1998');
insert into fauna(name, avg_age, discovery_date) values('Green snake', 4015, null);
insert into fauna(name, avg_age, discovery_date) values('Violet monkey', 19910, '26.08.1963');
insert into fauna(name, avg_age, discovery_date) values('Black fish', 14600, '13.02.1830');

select * from fauna where name like '%fish';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';