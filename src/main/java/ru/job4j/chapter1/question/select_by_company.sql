
/*
имена всех person, которые не состоят в компании с id = 5
+ название компании для каждого человека.
*/
select p.name, c.name from person p
join company c on p.company_id = c.id
where c.id != 5;

/*
название компании с максимальным количеством человек
+ количество человек в этой компании
(c учетом возможности существования нескольких компаний
  с одинаковым максимальным количеством человек).
*/
select c.name, count(p.id)
from company c
join person p
on c.id = p.company_id
group by c.name
having count(c.id) = (
	select count(*) as cnt
	from person group by company_id
	order by cnt desc fetch first row only)

