query = """
SELECT
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
	AND oi.product_id = p.product_id
"""
# Above query is a subquery so that's why parentheses are needed.