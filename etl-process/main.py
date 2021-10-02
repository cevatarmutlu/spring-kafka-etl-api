from pyspark.sql import SparkSession
from pyspark.sql.functions import *



appName = 'Python Spark SQL basic example'

spark_jars = '/home/sade/.local/share/DBeaverData/drivers/maven/maven-central/org.postgresql/postgresql-42.2.5.jar'

spark = SparkSession \
    .builder \
    .appName(appName) \
    .config("spark.jars", spark_jars) \
    .getOrCreate()


query = """
(SELECT
	oi.id,
	o.user_id,
	p.category_id,
	p.product_id
FROM
	order_items oi,
	orders o,
	products p
WHERE
	oi.order_id = o.order_id
	AND oi.product_id = p.product_id) temp
"""
# Yukarıdaki Query bir subquery' miş o yüzden öyle yazılıyor


format_ = 'jdbc'
url = 'jdbc:postgresql://localhost:5432/data-db'
user = 'postgres'
password = '123456'
driver = 'org.postgresql.Driver'


read_table = spark.read \
    .format(format_) \
    .option("url", url) \
    .option("dbtable", query) \
    .option("user", user) \
    .option("password", password) \
    .option("driver", driver) \
    .load()

read_table.printSchema()


user_splited = split(read_table["user_id"], "-")
category_splited = split(read_table["category_id"], "-")
product_splited = split(read_table["product_id"], "-")


etl_df = read_table.select(
    "id",
    user_splited.getItem(1).cast("integer").alias("user_id"),
    category_splited.getItem(1).cast("integer").alias("category_id"),
    product_splited.getItem(1).cast("integer").alias("product_id")
)

etl_df.printSchema()


# Şimdi elde ettiğimiz DF' ı PostgreSQL' e yazalım.
# Burası tam olarak ne yapar bilmiyorum.

write_table = 'bestseller_product'

etl_df.write.format(format_) \
    .option("url", url) \
    .option("dbtable", write_table) \
    .option("user", user) \
    .option("password", password) \
    .option("driver", driver) \
    .mode('append') \
    .save()