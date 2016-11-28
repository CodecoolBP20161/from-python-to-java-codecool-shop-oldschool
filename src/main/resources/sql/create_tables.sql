DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;


CREATE TABLE product_categories
(
  id          VARCHAR(36) PRIMARY KEY,
  name        VARCHAR(40),
  department  VARCHAR(10),
  description VARCHAR(500)
);

CREATE TABLE suppliers
(
  id   VARCHAR(36) PRIMARY KEY,
  name VARCHAR(40)
);

CREATE TABLE products
(
  id               VARCHAR(36) PRIMARY KEY,
  name             VARCHAR(40),
  description      VARCHAR(500),
  default_price    VARCHAR(10),
  default_currency VARCHAR(10),
  product_category VARCHAR(10) REFERENCES product_categories (id),
  supplier         VARCHAR(10) REFERENCES suppliers (id)
);
