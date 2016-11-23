DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;

CREATE TABLE product_category(
  id INT PRIMARY KEY     NOT NULL,
  name VARCHAR(100),
  description VARCHAR(200),
  department VARCHAR(200)
--   highestStudent_id integer REFERENCES students (id)
);

CREATE TABLE supplier (
  id INT PRIMARY KEY NOT NULL,
  NAME VARCHAR(100),
  description VARCHAR(200)
);

CREATE TABLE product (
  id                  INT PRIMARY KEY NOT NULL,
  name                VARCHAR(100),
  default_price       FLOAT,
  default_currency    VARCHAR(30),
  description         VARCHAR(200),
  product_Category_id INTEGER REFERENCES product_category (id),
  supplier_id         INTEGER REFERENCES supplier (id)
);



