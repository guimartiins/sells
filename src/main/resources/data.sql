CREATE TABLE IF NOT EXISTS client (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS product (
   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   description VARCHAR(100),
    price NUMERIC(20,2)
);

CREATE TABLE IF NOT EXISTS "ORDERS" (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    client_id INT REFERENCES client (id),
    created_at TIMESTAMP,
    total NUMERIC(20,2)
);

CREATE TABLE IF NOT EXISTS order_item (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id INT REFERENCES "ORDERS" (id),
    product_id INT REFERENCES product (id),
    quantity INT
);




