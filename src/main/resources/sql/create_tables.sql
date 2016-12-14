DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS line_items;

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
CREATE TABLE customers
(
  id               INT PRIMARY KEY,
  name             VARCHAR(100),
  email            VARCHAR(40),
  phone            VARCHAR(20),
  billing_country  VARCHAR(100),
  billing_city     VARCHAR(100),
  billing_zipcode  VARCHAR(100),
  billing_address   VARCHAR(100),
  shipping_country VARCHAR(100),
  shipping_city    VARCHAR(100),
  shipping_zipcode VARCHAR(100),
  shipping_address  VARCHAR(100)
);

CREATE TABLE orders
(
  id               INT PRIMARY KEY,
  order_status     VARCHAR(40),
  customer         INT REFERENCES customers (id)
);

CREATE TABLE line_items
(
  id               INT PRIMARY KEY,
  order_id         INT REFERENCES orders (id),
  product          INT REFERENCES products (id),
  quantity         INT,
  subtotal_price   DOUBLE PRECISION
);



