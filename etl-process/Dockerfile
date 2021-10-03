FROM python:3.8.12-alpine3.14

RUN apk add --no-cache curl build-base bash cmake openjdk11

#RUN java --version
#RUN which java
#RUN python -c "from distutils.sysconfig import get_python_lib; print(get_python_lib())"

RUN which java
RUN ls /usr/bin/java
ENV JAVA_HOME=/usr
ENV PATH="$JAVA_HOME/bin:${PATH}"

COPY . /app

WORKDIR /app

RUN pip install -r requirements.txt
ENV SPARK_HOME=/usr/local/lib/python3.8/site-packages/pyspark
ENV PATH="$SPARK_HOME/bin:${PATH}"
ENV PYSPARK_SUBMIT_ARGS="--master spark://192.168.2.40:7077"

RUN ls
RUN curl https://jdbc.postgresql.org/download/postgresql-42.2.5.jar --output postgresql-42.2.5.jar


ENTRYPOINT ["spark-submit", "--driver-class-path", "postgresql-42.2.5.jar", "main.py"]
