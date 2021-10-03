FROM openjdk:11.0.12-jre

VOLUME /var/conf
ADD ./target/view-producer*.jar view-producer.jar
RUN sh -c 'touch /view-producer.jar'

EXPOSE 8092

ENTRYPOINT [ "sh", "-c","java -XX:+UseStringDeduplication -jar /view-producer.jar" ]
