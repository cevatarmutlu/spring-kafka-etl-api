## View-Producer

View-Producer reads `product-views.json` file by line by and transform the line to `Event` class and writes to Kafka.

## Diagram

![diagram](images/view-producer_diagram.jpg)

## Dependencies

    Java-11
    Maven
    Kafka

## Install
    
    mvn clean install


## Run

First you write path of `product-views.json` to `json.path` in `application.properties` file at `src/resources`.

And then, you write `kafka.bootstrapAddress`, `kafka.topic` in `application.properties`.

For execute the project, in root directory run below command:

    mvn spring-boot:run