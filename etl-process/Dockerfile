FROM python:3.8.12-alpine3.14

RUN apk add --no-cache curl build-base bash cmake openjdk11

ENV JAVA_HOME=/usr
ENV PATH="$JAVA_HOME/bin:${PATH}"

COPY . /app

WORKDIR /app

RUN pip install -r requirements.txt
ENV SPARK_HOME=/usr/local/lib/python3.8/site-packages/pyspark
ENV PATH="$SPARK_HOME/bin:${PATH}"
ENV PYSPARK_SUBMIT_ARGS="--master spark://192.168.2.40:7077"

ENTRYPOINT ["spark-submit", "--driver-class-path", "src/jars/postgresql-42.2.5.jar", "main.py"]
