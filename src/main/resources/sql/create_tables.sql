DROP TABLE IF EXISTS products cascade;
DROP TABLE IF EXISTS product_categories cascade;
DROP TABLE IF EXISTS suppliers cascade;
DROP TABLE IF EXISTS orders cascade;
DROP TABLE IF EXISTS customers cascade;
DROP TABLE IF EXISTS line_items cascade;

CREATE TABLE product_categories
(
  id          INT PRIMARY KEY,
  name        VARCHAR(40),
  description VARCHAR(500),
  department  VARCHAR(50)
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
  default_price    VARCHAR(50),
  default_currency VARCHAR(50),
  product_category INT REFERENCES product_categories (id),
  supplier         INT REFERENCES suppliers (id)
);
CREATE TABLE customers
(
  id               INT UNIQUE PRIMARY KEY,
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

CREATE TYPE status AS ENUM ('IN_CART','CHECKED_OUT','PAID');

CREATE TABLE orders
(
  id               INT UNIQUE PRIMARY KEY,
  order_status     status DEFAULT 'IN_CART',
  customer         INT REFERENCES customers (id)
);


CREATE TABLE line_items
(
  id               INT UNIQUE primary key,
  order_id         INT REFERENCES orders (id),
  product          INT REFERENCES products (id),
  quantity         INT,
  subtotal_price   FLOAT
);



