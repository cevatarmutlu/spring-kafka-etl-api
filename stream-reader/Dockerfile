FROM openjdk:11.0.12-jre

VOLUME /var/conf
ADD ./target/stream-reader*.jar reader.jar
RUN sh -c 'touch /reader.jar'

EXPOSE 8070

ENTRYPOINT [ "sh", "-c","java -XX:+UseStringDeduplication -jar /reader.jar" ]
