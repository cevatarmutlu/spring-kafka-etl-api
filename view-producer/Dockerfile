FROM openjdk:11.0.12-jre-slim-buster

VOLUME /var/conf
ADD ./target/demo*.jar demo.jar
RUN sh -c 'touch /demo.jar'

EXPOSE 8080

ENTRYPOINT [ "sh", "-c","java -XX:+UseStringDeduplication -jar /demo.jar" ]
