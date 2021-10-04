## View-Producer

View-Producer reads `product-views.json` file by line by and transform the line to `Event` class and writes to Kafka.

Code | README | Unittest | Containerization
---- | ------ | -------- | ----------------
:heavy_check_mark: | :heavy_check_mark: | :x: | :heavy_check_mark:

## Diagram

![diagram](images/view-producer_diagram.jpg)

## Dependencies

    Java-11
    Maven
    Kafka

## Install

### Local
    
    mvn clean install

### Docker
    
    docker build . --tag case/producer

## Run

### Local

For execute the project, in root directory run below command:

    mvn spring-boot:run

### Docker
    
    docker run -p 8091:8091 --network='host' case/producer

For started your send a request:
    
    http://localhost:8091/api/producer/start
