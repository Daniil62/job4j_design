CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    producer VARCHAR,
    count INTEGER DEFAULT 0,
    price INTEGER
);

CREATE OR REPLACE PROCEDURE delete_products(threshold INTEGER)
LANGUAGE 'plpgsql'
AS $$
BEGIN
DELETE FROM products WHERE count <= threshold;
END;
$$;

CALL delete_products(0);


CREATE OR REPLACE FUNCTION f_delete_products(threshold INTEGER)
RETURNS VOID
LANGUAGE 'plpgsql'
AS $$
BEGIN
DELETE FROM products WHERE count <= threshold;
END;
$$;

SELECT FROM f_delete_products(2);