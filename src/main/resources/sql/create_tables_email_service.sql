DROP TABLE IF EXISTS emails;


CREATE TABLE emails
(
  id SERIAL PRIMARY KEY,
  to_address VARCHAR(50),
  password VARCHAR(50),
  from_address VARCHAR(50),
  subject VARCHAR(50),
  message VARCHAR(500),
  status VARCHAR(50)
)