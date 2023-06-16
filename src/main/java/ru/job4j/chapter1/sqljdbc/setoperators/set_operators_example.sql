CREATE TABLE IF NOT EXISTS movie (
    id SERIAL PRIMARY KEY,
    name TEXT,
    director TEXT
);

CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title TEXT,
    author TEXT
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');


#  вывод названия всех фильмов, которые сняты по книге.

SELECT name FROM movie
INTERSECT
SELECT title FROM book
ORDER BY name;


#  вывод всех названий книг, у которых нет экранизации;

SELECT title FROM book
EXCEPT
SELECT name FROM movie
ORDER BY title;


#  вывод всех уникальных названий произведений из таблиц movie и book (фильмы снятые не по книге, книги без экранизации)

SELECT title FROM book
UNION
SELECT name FROM movie
EXCEPT
(SELECT title FROM book
INTERSECT
SELECT name FROM movie);