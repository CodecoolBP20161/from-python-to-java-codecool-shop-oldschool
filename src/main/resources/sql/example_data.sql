INSERT INTO product_categories ( id, name, department, description ) VALUES
  ( 1,'tablet', 'yes', 'you dont need it' ), ( 2,'phone', 'unknown', 'dont buy it' );
INSERT INTO suppliers ( id, name, description ) VALUES
  ( 1, 'huwuwuwy', 'new desc'), ( 2, 'tyhansen', 'old desc');
INSERT INTO products ( id, name, description, default_price, default_currency, product_category, supplier ) VALUES
  ( 1, 'yoga', 'practice', '12', 'USD', 1, 1 ), ( 2, 'yoga', 'practice', '12', 'USD', 2, 2 );
