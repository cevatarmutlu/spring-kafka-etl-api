## ETL-Process

ETL-Process, implements ETL to `orders`, `products` and `order_items` tables. These tables joins `bestseller_product` table.

## Diagram

![diagram](images/etl_process.jpg)

## Dependencies

    Python3
    Java
    Spark

## Install

Download `postgresql-42.2.5.jar`:

    curl https://jdbc.postgresql.org/download/postgresql-42.2.5.jar --output postgresql-42.2.5.jar

Python modules:

    pip3 install -r requirements.txt

## Run

First you write database connection information to `config.yml` at `src` folder.

For execute the project, in root directory run belowe command:

    spark-submit --driver-class-path postgresql-42.2.5.jar main.py