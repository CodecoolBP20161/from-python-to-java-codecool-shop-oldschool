DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;


CREATE TABLE product_categories
(
  id          INT PRIMARY KEY,
  name        VARCHAR(40),
  description VARCHAR(500),
  department  VARCHAR(10)
);

CREATE TABLE suppliers
(
  id          INT PRIMARY KEY,
  name        VARCHAR(40),
  description VARCHAR(500)
);

CREATE TABLE products
(
  id               INT PRIMARY KEY,
  name             VARCHAR(40),
  description      VARCHAR(500),
  default_price    VARCHAR(10),
  default_currency VARCHAR(10),
  product_category INT REFERENCES product_categories (id),
  supplier         INT REFERENCES suppliers (id)
);
