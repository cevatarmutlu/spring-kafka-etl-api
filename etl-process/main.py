from pandas._config import config
from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import src.config as conf
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


    etl_df = df.select(
        "id",
        user_splited.getItem(1).cast("integer").alias("user_id"),
        category_splited.getItem(1).cast("integer").alias("category_id"),
        product_splited.getItem(1).cast("integer").alias("product_id")
    )

    return etl_df

def load(etl_df, conf, table):
    etl_df.write \
        .format(conf.get("format")) \
        .option("url", conf.get("url")) \
        .option("dbtable", table) \
        .option("user", conf.get("user")) \
        .option("password", conf.get("password")) \
        .option("driver", conf.get("driver")) \
        .mode('overwrite') \
        .save()


if __name__ == '__main__':
    db = conf.get("database")

    spark = SparkSession \
        .builder \
        .appName("etl process") \
        .config("spark.jars", "postgresql-42.2.5.jar") \
        .getOrCreate()
    
    read_table = extract(spark_session=spark, conf=db, query=query)

    etl_df = transform(df=read_table)

    load(etl_df=etl_df, conf=db, table="bestseller_product")
