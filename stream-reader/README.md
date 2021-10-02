## Stream-Reader

Stream-Reader reads `Event` from Kafka and transform the event to `BrowsingHistory` class and the class write to `PostgreSQL` Database.

## Diagram

![diagram](images/stream-reader-diagram.jpg)

## Dependencies
    
    Java-11
    Maven
    Kafka

## Install
    
    mvn clean install

## Run

First you write `kafka.bootstrapAddress`, `kafka.topic` and `kafka.group-id` in `application.properties` at `src/resources`.

And then, you write Database connection information in `application.properties`: `datasource.url`, `datasource.username`, `datasource.password`

For execute the project, in root directory run below command:

    mvn spring-boot:run