from pandas._config import config
from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import src.config as config
from src.query import query

def extract(spark_session, conf, query):
    return spark_session.read \
        .format(conf.get("format")) \
        .option("url", conf.get("url")) \
        .option("dbtable", f"({query}) tmp") \
        .option("user", conf.get("user")) \
        .option("password", conf.get("password")) \
        .option("driver", conf.get("driver")) \
        .load()


def transform(df):
    user_splited = split(df["user_id"], "-")
    category_splited = split(df["category_id"], "-")
    product_splited = split(df["product_id"], "-")


    return df.select(
        "id",
        user_splited.getItem(1).cast("integer").alias("user_id"),
        category_splited.getItem(1).cast("integer").alias("category_id"),
        product_splited.getItem(1).cast("integer").alias("product_id")
    )


def load(df, conf, table):
    df.write \
        .format(conf.get("format")) \
        .option("url", conf.get("url")) \
        .option("dbtable", table) \
        .option("user", conf.get("user")) \
        .option("password", conf.get("password")) \
        .option("driver", conf.get("driver")) \
        .mode('overwrite') \
        .save()


def main():
    conf = config.get("db")

    spark = SparkSession \
        .builder \
        .appName("etl process") \
        .config("spark.jars", "src/jars/postgresql-42.2.5.jar") \
        .getOrCreate()
    
    extracted_df = extract(spark_session=spark, conf=conf, query=query)

    transformed_df = transform(df=extracted_df)

    load(df=transformed_df, conf=conf, table="bestseller_product")

if __name__ == '__main__':
    main()