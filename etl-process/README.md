## ETL-Process

ETL-Process, implements ETL to `orders`, `products` and `order_items` tables. These tables joins `bestseller_product` table.

Code | README | Unittest | Containerization
---- | ------ | -------- | ----------------
:heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark:


## Diagram

![diagram](images/etl_process.jpg)

### Extract query of ETL

```sql
SELECT
	oi.id,
	o.user_id,
	p.category_id,
	p.product_id
FROM
	order_items oi,
	orders o,
	products p
WHERE
	oi.order_id = o.order_id
	AND oi.product_id = p.product_id
```

### Transformed data

before transformation:


column | value
------ | -----
id |  111
user-id | user-10
category-id | category-50
product-id | product-45

after transformation:

column | value
------ | -----
id |  111
user-id | 10
category-id | 50
product-id | 45

## Dependencies

    Python3
    Spark

## Install

### Local

    pip3 install -r requirements.txt

### Docker
For execute the project, in root directory run belowe command:

    docker build . --tag case/etl_process

## Run

### Local

For execute the project, in root directory run belowe command:

    python3 main.py

### Docker

    docker run --network='host' case/etl_process