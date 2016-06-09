
-- 1. Select all Canadian providers
SELECT DISTINCT name Canadian_providers FROM providers 
WHERE address_id in (
	SELECT address_id FROM address
	WHERE country = 'Canada');
	
-- 2. Select all storage of 4$(or hight) goods
SELECT DISTINCT storages.name storage_name, selected_goods.name goods, selected_goods.price price_USD
FROM storages, (
	SELECT name, price_USD price  FROM goods
	WHERE price_USD = 4::money
) selected_goods
ORDER BY goods;

-- 3. Select all providers who in busines after 01.01.1990
SELECT DISTINCT name providers_after_1990 FROM providers 
WHERE in_busines_at >= DATE '1990-01-01';

-- 4. Select delivery and providers dependency
SELECT DISTINCT providers.name providers, delivery.name delivery
FROM providers 
RIGHT JOIN delivery_companies delivery
  ON providers.delivery_company_id = delivery.delivery_company_id;
  

-- 5. Select goods & price provided in Vancouver
SELECT DISTINCT goods.name goods, goods.price_USD price_USD
FROM goods
WHERE good_id in (
	SELECT good_id FROM providers
	WHERE address_id in (
		SELECT address_id FROM address
		WHERE city = 'Vancouver'
	)
);