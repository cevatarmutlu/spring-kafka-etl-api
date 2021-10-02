package com.hepsiburada.api.repository;

import com.hepsiburada.api.model.BestsellerProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BestsellerProductRepository extends CrudRepository<BestsellerProduct, Integer> {

    @Query(
            value = "SELECT foo.product_id FROM (\n" +
                        "SELECT user_id, product_id FROM bestseller_product \n" +
                        "WHERE category_id IN (\n" +
                            "SELECT category_id FROM browsing_history bh \n" +
                            "WHERE user_id = :userId\n" +
                            "GROUP BY category_id, product_id\n" +
                            "ORDER BY count(product_id) DESC\n" +
                            "LIMIT 3\n" +
                        ")\n" +
                        "GROUP BY user_id, product_id\n" +
                    ") AS foo\n" +
                    "GROUP BY product_id, user_id\n" +
                    "ORDER BY count(*) DESC \n" +
                    "LIMIT 10",
            nativeQuery = true
    )
    List<?> getBestsellerProductsIfUserHistoryExist(@Param("userId") int userId);


    @Query(
            value = "SELECT foo.product_id FROM (\n" +
                        "SELECT user_id, product_id FROM bestseller_product\n" +
                        "GROUP BY user_id, product_id" +
                    ") AS foo\n" +
                    "GROUP BY product_id, user_id\n" +
                    "ORDER BY count(*) DESC\n" +
                    "LIMIT 10",
            nativeQuery = true
    )
    List<?> getBestsellerProductsIfUserHistoryNotExist();

}
