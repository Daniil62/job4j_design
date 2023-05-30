CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    producer VARCHAR,
    count INTEGER DEFAULT 0,
    price INTEGER
);

CREATE TABLE IF NOT EXISTS history_of_price (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    price INTEGER,
    date TIMESTAMP
);

CREATE OR REPLACE FUNCTION postfix_tax()
RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price + price * 0.2
        WHERE id IN (SELECT id FROM inserted);
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION prefix_tax()
RETURNS TRIGGER AS
$$
    BEGIN
		NEW.price = NEW.price + NEW.price * 0.2;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION fix_for_history()
RETURNS TRIGGER AS
$$
    BEGIN
        INSERT INTO history_of_price(name, price, date)
        VALUES(NEW.name, NEW.price, NOW());
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER postfix_tax_trigger
    AFTER INSERT ON products
	REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE postfix_tax();

CREATE TRIGGER prefix_tax_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE prefix_tax();

CREATE TRIGGER history_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE fix_for_history();

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 40, 500);
SELECT * FROM products;
SELECT * FROM history_of_price;

-- Выключение и включение триггера:
-- ALTER TABLE products DISABLE TRIGGER history_trigger;
-- ALTER TABLE products ENABLE TRIGGER history_trigger;

-- Удаление триггера:
-- DROP TRIGGER history_trigger ON products;