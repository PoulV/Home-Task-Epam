CREATE TABLE address (
  address_id SERIAL PRIMARY KEY,
  country VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  address VARCHAR(50) NOT NULL
);

CREATE TABLE goods (
  good_id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  container VARCHAR(50) NOT NULL,
  dimension VARCHAR(50) NOT NULL,  
  weight_kg DECIMAL CONSTRAINT positive_weight CHECK (weight_kg > 0) NOT NULL,
  price_USD MONEY CONSTRAINT positive_price CHECK (price_USD > 0::MONEY)
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
  good_id INT REFERENCES goods(good_id),
  area_square_yard DECIMAL NOT NULL
);

CREATE TABLE contracts (
  contract_id SERIAL PRIMARY KEY,
  provider_id INT REFERENCES providers(provider_id),
  storage_id INT REFERENCES storages(storage_id),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  description VARCHAR(50)
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

INSERT INTO goods (name, container, dimension, weight_kg, price_USD)
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

INSERT INTO storages (name, address_id, good_id, area_square_yard)
VALUES 	('USA National Spoon And Fork Storage', 4, 3, 250),	
	('USA National Spoon And Fork Storage', 4, 4, 250),
	('USA National Spoon And Fork Storage', 4, 5, 250),
	('Sacramento Greate Spoon Storage', 9, 3, 300),
	('NV Boots', 11, 1, 150),
	('NV Boots', 11, 2, 150),
	('Manific Boots Storage', 4, 2, 123.5);

INSERT INTO contracts ( provider_id, storage_id, start_date, end_date, description)
VALUES	(3, 1, DATE '2000-02-19', DATE '2050-02-19', NULL),
	(1, 2, DATE '2000-02-19', DATE '2050-02-19', NULL),
	(2, 3, DATE '2000-02-19', DATE '2050-02-19', NULL),
	(3, 4, DATE '2016-01-01', DATE '2017-01-01', 'Ask John'),
	(4, 5, DATE '2016-05-19', DATE '2016-09-01', NULL),
	(5, 6, DATE '2016-05-19', DATE '2016-09-18', NULL),
	(4, 7, DATE '2016-02-19', DATE '2017-02-19', 'entrance from the yard');
