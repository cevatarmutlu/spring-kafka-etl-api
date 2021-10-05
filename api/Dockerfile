FROM openjdk:11.0.12-jre

VOLUME /var/conf
ADD ./target/api*.jar api.jar
RUN sh -c 'touch /api.jar'

EXPOSE 8080

ENTRYPOINT [ "sh", "-c","java -XX:+UseStringDeduplication -jar /api.jar --spring.datasource.url=jdbc:postgresql://postgres:5432/data-db" ]
