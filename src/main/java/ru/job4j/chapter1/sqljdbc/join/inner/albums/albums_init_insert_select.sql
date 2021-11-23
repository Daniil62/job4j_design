create table band(
	id serial primary key,
	foundation_year int,
	name text
);
create table album(
	id serial primary key,
	name text,
	release_year int,
	band_id int references band(id)
);
insert into band(name, foundation_year) values('Mayhem', 1984);
insert into band(name, foundation_year) values('Darkthrone', 1986);
insert into band(name, foundation_year) values('Belphegor', 1991);

insert into album(name, release_year, band_id) values('De Mysteriis Dom Sathanas', 1994, 1);
insert into album(name, release_year, band_id) values('Transilvanian Hunger', 1994, 2);
insert into album(name, release_year, band_id) values('The Last Supper', 1995, 3);

select b.name, a.name, a.release_year from band as b join album as a on a.band_id = b.id;
select b.name as группа, a.name as альбом, a.release_year as год from band as b join album as a on a.band_id = b.id;
select a.name, a.release_year, b.name from album as a join band as b on a.band_id = b.id and b.foundation_year > 1985;