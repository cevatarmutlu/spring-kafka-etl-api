## Stream-Reader

Stream-Reader reads `Event` from Kafka and transform the event to `BrowsingHistory` class and the class write to `PostgreSQL` Database.

Unnecessary fields not selected in the event for generate `BrowsingHistory`.

`BrowsingHistory` has `category_id` because if `BrowsingHistory` has not `category_id`, to find most three category joins `browsing_history` and `products` table. So that's more costly to find most tree category than this solution. 

Code | README | Unittest | Containerization
---- | ------ | -------- | ----------------
:heavy_check_mark: | :heavy_check_mark: | :x: | :heavy_check_mark:

## Diagram

![diagram](images/stream-reader-diagram.jpg)

## Dependencies
    
    Java-11
    Maven
    Kafka

## Install

### Local
    
    mvn clean install

### Docker
    
    docker build --no-cache . --tag case/reader


## Run

### Local

For execute the project, in root directory run below command:

    mvn spring-boot:run

### Docker
    
    docker run -p 8092:8092 --network='host' case/reader

If you want to see the event that read from kafka so:
    
    http://localhost:8092/api/consumer/payload

If you want to see history that generate from the event so:
    
    http://localhost:8092/api/consumer/history