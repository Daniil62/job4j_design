CREATE TABLE IF NOT EXISTS customers(
    id SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    age INTEGER,
    country TEXT
);

CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    amount INTEGER,
    customer_id INTEGER REFERENCES customers(id)
);

INSERT INTO customers(first_name, last_name, age, country) VALUES('Иван', 'Иванов', 30, 'Россия');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Елена', 'Петрова', 28, 'Россия');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Лао', 'Чжен', 27, 'Китай');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Мэй', 'Чжоу', 28, 'Китай');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Никола', 'Петрович', 35, 'Сербия');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Милица', 'Йованович', 34, 'Сербия');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Диванш', 'Кумар', 36, 'Индия');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Бхавия', 'Чопра', 33, 'Индия');

SELECT * FROM customers c WHERE c.age = (SELECT MIN(age) from customers);

INSERT INTO orders(amount, customer_id) VALUES(25000, 2);
INSERT INTO orders(amount, customer_id) VALUES(18000, 4);
INSERT INTO orders(amount, customer_id) VALUES(7000, 5);
INSERT INTO orders(amount, customer_id) VALUES(15000, 7);

SELECT * FROM customers c WHERE c.id NOT IN (SELECT customer_id FROM orders);