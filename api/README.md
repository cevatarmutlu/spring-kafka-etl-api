## API

API, return data below request:

* Browsing History
  * Last ten products viewed by a given user. GET endpoint, inputs: user-id
  * Delete a product from given user. DELETE endpoint, inputs: user-id, product-id
* Best Seller Products: GET endpoint, inputs: user-id
  * If given user has history, using the user history for extract most three category. Then use these categories to recommendation ten products bought (last month) by the most distinct users.
  * If given user has not history, recommend ten products bought (last month) by the most distinct users.


Code | README | Unittest | Containerization
---- | ------ | -------- | ----------------
:heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark:

## Diagram

![diagram](images/diagram.jpg)

### Browsing History

#### GET Request

Last ten products viewed by a given user.

`inputs: user-id`

##### Example Request and Response

Request:

    http://localhost:8080/api/history/products?userId=111

Response:

    {
      "user-id": "111",
      "products": [10, 11, 12, 13, 14, 15, 16, 17, 18, 19],
      "type": "personalized"
    }

 If number of products fewer than 5, return Empty list. 

##### Query

```sql
SELECT product_id FROM browsing_history
WHERE user_id = :userId
ORDER BY timestamp DESC
LIMIT 10
```

#### DELETE Request

Delete a product from given user.

`inputs: userId, productId`

##### Example Request and Response

Request:

    http://localhost:8080/api/history/delete?userId=84&productId=13

Response:
    
    {
      "user-id": 84,
      "product-id": 3,
      "success": false
    }

If product deleted is unsuccessful then return `"success": false`.

##### Query

```sql
DELETE FROM browsing_history
WHERE user_id = :userId AND product_id = :productId
```

### Bestseller Product

`inputs: userId`

#### GET REQUEST: If user has history

If given user has history, using the user history for extract most three category. Then use these categories to recommendation ten products bought (last month) by the most distinct users.

##### Example Request and Response

Request:
  
    http://localhost:8080/api/bestseller/products?userId=84

Response:

    {
      "user-id": 84,
      "products": [66, 158, 182, 45, 44, 31, 35, 72, 30, 59],
      "type": "personalized"
    }

##### Query

```sql
SELECT foo.product_id FROM (
    SELECT user_id, product_id FROM bestseller_product
    WHERE category_id IN (
        SELECT category_id FROM browsing_history
        WHERE user_id = 84
        GROUP BY category_id, product_id
        ORDER BY count(product_id) DESC
        LIMIT 3
    )
    GROUP BY user_id, product_id
    ) AS foo
GROUP BY product_id
ORDER BY count(*) DESC
LIMIT 10
```

#### GET REQUEST: If user has not history

If given user has not history, recommend ten products bought (last month) by the most distinct users.

##### Example Request and Response

Request:

    http://localhost:8080/api/bestseller/products?userId=840

Response:

    {
      "user-id": 84000,
      "products": [170, 48, 83, 66, 188, 52, 11, 168, 39, 158],
      "type": "non-personalized"
    }

##### Query

```sql
SELECT foo.product_id FROM (
    SELECT user_id, product_id FROM bestseller_product
    GROUP BY user_id, product_id
    ) AS foo
GROUP BY product_id
ORDER BY count(*) DESC
LIMIT 10
```

## Dependencies

    Java-11
    Maven

## Install

### Local

    mnv clean install

### Docker

At this project root directory:

    docker build . --tag case/api

## Run

### Local

    mvn spring-boot:run

### Docker

    docker run -p 8080:8080 --network="host" case/api
