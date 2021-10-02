from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import src.config as conf
from src.query import query


if __name__ == '__main__':
    db = conf.get("database")

    spark = SparkSession \
        .builder \
        .appName("etl process") \
        .getOrCreate()
    
    ##### Extract #####
    read_table = spark.read \
        .format(db.get("format")) \
        .option("url", db.get("url")) \
        .option("dbtable", f"({query}) tmp") \
        .option("user", db.get("user")) \
        .option("password", db.get("password")) \
        .option("driver", db.get("driver")) \
        .load()
    

    ##### Transform #####
    user_splited = split(read_table["user_id"], "-")
    category_splited = split(read_table["category_id"], "-")
    product_splited = split(read_table["product_id"], "-")


    etl_df = read_table.select(
        "id",
        user_splited.getItem(1).cast("integer").alias("user_id"),
        category_splited.getItem(1).cast("integer").alias("category_id"),
        product_splited.getItem(1).cast("integer").alias("product_id")
    )

    ##### Load #####
    etl_df.write \
        .format(db.get("format")) \
        .option("url", db.get("url")) \
        .option("dbtable", db.get("table")) \
        .option("user", db.get("user")) \
        .option("password", db.get("password")) \
        .option("driver", db.get("driver")) \
        .mode('append') \
        .save()