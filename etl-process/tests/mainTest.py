import unittest

from pyspark.sql import SparkSession
from main import extract, transform, load
import src.config as config
from src.query import query
from pyspark.sql.types import StructType,StructField, StringType, IntegerType
import pandas as pd

class TestMain(unittest.TestCase):
    def setUp(self) -> None:
        self.spark = SparkSession.builder.config("spark.jars", "src/jars/postgresql-42.2.5.jar").getOrCreate()
        self.conf = config.get("db")
    

    def test_extract(self):

        query = 'SELECT * FROM orders LIMIT 3'

        extracted_df = extract(spark_session=self.spark, conf=self.conf, query=query)

        self.assertEqual(extracted_df.count(), 3)


    def test_transform(self):

        data = [
            (15, "user-15", "product-15", "category-15"),
            (16, "user-16", "product-16", "category-16"),
            (17, "user-17", "product-17", "category-17")
        ]

        schema = StructType([
            StructField("id", IntegerType(), True),
            StructField("user_id", StringType(), True),
            StructField("product_id", StringType(), True),
            StructField("category_id", StringType(), True),
        ])

        df = self.spark.createDataFrame(data=data,schema=schema)

        post_etl_data = [
            (15, 15, 15, 15),
            (16, 16, 16, 16),
            (17, 17, 17, 17)
        ]

        schema = StructType([
            StructField("id", IntegerType(), True),
            StructField("user_id", IntegerType(), True),
            StructField("category_id", IntegerType(), True),
            StructField("product_id", IntegerType(), True),
        ])

        expected_df = self.spark.createDataFrame(data=post_etl_data, schema=schema)

        transformed_df = transform(df=df)

        self.assertEqual(transformed_df.count(), expected_df.count())
        pd.testing.assert_frame_equal(transformed_df.toPandas() ,expected_df.toPandas())

    
    def test_load(self):

        data = [
            (1, "a", "b"),
            (2, "c", "d")
        ]
        schema = StructType([
            StructField("id", IntegerType(), True),
            StructField("user", StringType(), True),
            StructField("password", StringType(), True)
        ])

        dummy_df = self.spark.createDataFrame(data=data, schema=schema)

        load(df=dummy_df, conf=self.conf, table='deneme')

        expected_df = extract(self.spark, self.conf, 'select * from deneme order by id')
        ## Spark veriyi kendisi sıralıyor. Gelen veri doğru olmasına rağmen test' ten geçmiyor.

        self.assertEqual(expected_df.count(), dummy_df.count())
        pd.testing.assert_frame_equal(expected_df.toPandas(), dummy_df.toPandas())