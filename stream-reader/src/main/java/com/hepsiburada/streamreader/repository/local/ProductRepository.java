package com.hepsiburada.streamreader.repository.local;

import com.hepsiburada.streamreader.model.local.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    @Query(
            value = "SELECT category_id FROM products WHERE product_id = :productId",
            nativeQuery = true
    )
    List<?> getCategoryId(@Param("productId") String productId);

}
