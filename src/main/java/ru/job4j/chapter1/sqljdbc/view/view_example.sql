CREATE TABLE IF NOT EXISTS engine_type(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS engine(
     id SERIAL PRIMARY KEY,
     type_id INT REFERENCES engine_type(id),
     volume NUMERIC,
     horsepower INT
);

CREATE TABLE IF NOT EXISTS transmission_type(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS drive_type(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS transmission(
     id SERIAL PRIMARY KEY,
     type_id INT REFERENCES transmission_type(id),
     gears INT,
     drive_type_id INT REFERENCES drive_type(id)
);

CREATE TABLE IF NOT EXISTS body_type(
     id SERIAL PRIMARY KEY,
     type VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS colour(
     id SERIAL PRIMARY KEY,
     name VARCHAR NOT NULL,
     hex VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS body(
     id SERIAL PRIMARY KEY,
     type_id INT REFERENCES body_type(id),
     colour_id INT REFERENCES colour(id)
);

CREATE TABLE IF NOT EXISTS brand(
     id SERIAL PRIMARY KEY,
     name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS model(
     id SERIAL PRIMARY KEY,
     name VARCHAR NOT NULL UNIQUE,
     brand_id INT REFERENCES brand(id)
);

CREATE TABLE IF NOT EXISTS vehicle(
    id SERIAL PRIMARY KEY,
    model_id INT NOT NULL REFERENCES model(id),
    engine_id INT REFERENCES engine(id),
    transmission_id INT NOT NULL REFERENCES transmission(id),
    body_id INT REFERENCES body(id),
    mileage INT,
    owners_count INT
);

CREATE TABLE IF NOT EXISTS ad(
    id SERIAL PRIMARY KEY,
    header VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    vehicle_id INT REFERENCES vehicle(id),
    is_sold BOOL,
    created TIMESTAMP NOT NULL,
    price NUMERIC
);

INSERT INTO engine_type(type) VALUES('gasoline');
INSERT INTO engine_type(type) VALUES('diesel');
INSERT INTO engine_type(type) VALUES('electro');

INSERT INTO engine(type_id, volume, horsepower) VALUES(1, 1.5, 170);
INSERT INTO engine(type_id, volume, horsepower) VALUES(1, 3.0, 275);
INSERT INTO engine(type_id, volume, horsepower) VALUES(1, 1.2, 90);
INSERT INTO engine(type_id, volume, horsepower) VALUES(2, 2.5, 250);
INSERT INTO engine(type_id, volume, horsepower) VALUES(2, 1.5, 200);
INSERT INTO engine(type_id, volume, horsepower) VALUES(3, 0, 350);

INSERT INTO transmission_type(type) VALUES('manual');
INSERT INTO transmission_type(type) VALUES('automatic');
INSERT INTO transmission_type(type) VALUES('robot');
INSERT INTO transmission_type(type) VALUES('variator');

INSERT INTO drive_type(type) VALUES('front');
INSERT INTO drive_type(type) VALUES('rear');
INSERT INTO drive_type(type) VALUES('full');
INSERT INTO drive_type(type) VALUES('variative');

INSERT INTO transmission(type_id, gears, drive_type_id) values(3, 5, 1);
INSERT INTO transmission(type_id, gears, drive_type_id) values(2, 5, 2);
INSERT INTO transmission(type_id, gears, drive_type_id) values(3, 5, 2);
INSERT INTO transmission(type_id, gears, drive_type_id) values(1, 5, 2);
INSERT INTO transmission(type_id, gears, drive_type_id) values(1, 6, 2);
INSERT INTO transmission(type_id, gears, drive_type_id) values(4, 7, 4);

INSERT INTO body_type(type) VALUES('sedan');
INSERT INTO body_type(type) VALUES('sedan convertible');
INSERT INTO body_type(type) VALUES('hatchback');
INSERT INTO body_type(type) VALUES('coupe');
INSERT INTO body_type(type) VALUES('coupe convertible');

INSERT INTO colour(name, hex) VALUES('white', '#FFFFFF');
INSERT INTO colour(name, hex) VALUES('blue', '#0000FF');
INSERT INTO colour(name, hex) VALUES('red', '#FF0000');
INSERT INTO colour(name, hex) VALUES('black', '#000000');
INSERT INTO colour(name, hex) VALUES('green', '#00FF00');

INSERT INTO body(type_id, colour_id) VALUES(1, 5);
INSERT INTO body(type_id, colour_id) VALUES(2, 4);
INSERT INTO body(type_id, colour_id) VALUES(3, 3);
INSERT INTO body(type_id, colour_id) VALUES(4, 4);
INSERT INTO body(type_id, colour_id) VALUES(5, 4);

INSERT INTO brand(name) values('First Brand');
INSERT INTO brand(name) values('Second Brand');

INSERT INTO model(name, brand_id) values('Model A', 1);
INSERT INTO model(name, brand_id) values('Model B', 1);
INSERT INTO model(name, brand_id) values('Model C', 1);
INSERT INTO model(name, brand_id) values('Model A2', 2);
INSERT INTO model(name, brand_id) values('Model B2', 2);
INSERT INTO model(name, brand_id) values('Model C2', 2);

INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(1, 4, 1, 1, 50000, 1);
INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(2, 5, 2, 2, 60000, 2);
INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(3, 3, 3, 3, 70000, 3);
INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(4, 1, 5, 4, 50000, 1);
INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(5, 2, 4, 5, 60000, 1);
INSERT INTO vehicle(model_id, engine_id, transmission_id, body_id, mileage, owners_count)VALUES(6, 3, 4, 5, 40000, 3);

INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 1, '2023-05-26', 1000000.0);
INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 2, '2023-04-28', 750000.0);
INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 3, '2023-05-09', 2000000.0);
INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 4, '2023-05-25', 1500000.0);
INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 5, '2023-05-27', 650000.0);
INSERT INTO ad(header, description, vehicle_id, created, price)VALUES('Header', 'Description', 6, '2023-05-26', 2300000.0);

CREATE VIEW findLatestAdsWithBlackCoupeLess300HPEngineManualRearDrive
AS SELECT date_trunc('day', ad.created) AS ad_date, b.name AS brand, m.name AS model
FROM ad
JOIN vehicle v ON ad.vehicle_id = v.id
JOIN model m ON v.model_id = m.id
JOIN brand b ON m.brand_id = b.id
JOIN engine e ON v.engine_id = e.id
JOIN engine_type et ON e.type_id = et.id
JOIN transmission t ON v.transmission_id = t.id
JOIN transmission_type tt ON t.type_id = tt.id
JOIN drive_type dt ON t.drive_type_id = dt.id
JOIN body bo ON v.body_id = bo.id
JOIN body_type bt ON bo.type_id = bt.id
JOIN colour c ON bo.colour_id = c.id
WHERE ad.created >= NOW() - INTERVAL '1 week'
AND e.horsepower <= 300
AND et.type = 'gasoline'
AND tt.type = 'manual'
AND dt.type = 'rear'
AND bt.type IN ('coupe', 'coupe convertible')
AND c.name = 'black'
GROUP BY ad_date, brand, model
ORDER BY ad_date ASC;