CREATE TABLE address (
  address_id SERIAL PRIMARY KEY,
  country VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  address VARCHAR(50) NOT NULL
);

CREATE TABLE goods (
  good_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  contaiver VARCHAR(50) NOT NULL,
  dimension VARCHAR(50) NOT NULL,  
  weight_kg decimal CONSTRAINT positive_price CHECK (weight_kg > 0) NOT NULL,
  price_USD MONEY
);

CREATE TABLE delivery_companies (
  delivery_company_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(50),
  address_id INT REFERENCES address(address_id),
  insurance BOOLEAN NOT NULL
);

CREATE TABLE providers (
  provider_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  address_id INT REFERENCES address(address_id),  
  good_id INT REFERENCES goods(good_id),
  delivery_company_id INT REFERENCES delivery_companies(delivery_company_id),
  in_busines_at DATE
);

CREATE TABLE storages (
  storage_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  address_id INT REFERENCES address(address_id),
  provider_id INT REFERENCES providers(provider_id),
  good_id INT REFERENCES goods(good_id)
);

INSERT INTO address (country, city, address)
VALUES 	('USA', 'New York', '1st ave'),
	('USA', 'New York', '58 street 54'),
	('USA', 'New York', '332/54d'),
	('Canada', 'Vancouver', 'Deju str. 58/32'),
	('Canada', 'Vancouver', 'Alde str. 15/142'),
	('USA', 'Sacramento', '856 39th Street'),
	('USA', 'San Francisco', '1717 Harrison St'),	
	('USA', 'Las Vegas', '501 S Rancho Dr #A8'),
	('USA', 'Sacramento', '458 31th Street'),
	('USA', 'San Francisco', '200 McAllister St'),	
	('USA', 'Las Vegas', '309 S Valley View Blvd');

INSERT INTO goods (name, contaiver, dimension, weight_kg, price_USD)
VALUES 	('Soft boots', 'box', '30 x 15 x 10', 1.5, 1),
	('Heavy boots', 'box', '35 x 20 x 15', 3.5, 1.5),
	('Silver spoon', 'box', '15 x 7 x 1,5', 0.3, 4),
	('Silver fork', 'box', '15 x 7 x 1,5', 0.3, 4),
	('Silver knife', 'box', '15 x 7 x 1,5', 0.3, 4);
	
INSERT INTO delivery_companies (name, description, address_id, insurance)
VALUES 	('NY Delivery inc.', NULL, 1, TRUE),
	('Canadian Delivery Brotherhood', 'do not trust them', 5, FALSE),
	('California Delivery', 'very fast', 6, TRUE),
	('San Francisco Birds inc.', NULL, 7, TRUE),
	('Las Vegas Post', 'very slow', 8, FALSE);

INSERT INTO providers ( name, address_id, good_id, delivery_company_id, in_busines_at)
VALUES	('Cutlery John & Sons', 1, 4, 1, DATE '2000-02-19'),
	('Cutlery John & Sons', 1, 5, 1, DATE '2000-02-19'),
	('SpoonMaster 3000', 2, 3, 1, DATE '1985-06-20'),	
	('Dejounte Boots', 4, 1, 2, DATE '2010-10-12'),
	('Dejounte Boots', 4, 2, 2, DATE '2010-10-12');

INSERT INTO storages (name, address_id, provider_id, good_id)
VALUES 	('USA National Spoon And Fork Storage', 4, 1, 1),	
	('USA National Spoon And Fork Storage', 4, 1, 2),
	('USA National Spoon And Fork Storage', 4, 1, 3),
	('Sacramento Greate Spoon Storage', 9, 3, 3),
	('NV Boots', 11, 4, 1),
	('NV Boots', 11, 5, 2),
	('Manific Boots Storage', 4, 2, 2);
