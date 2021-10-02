package com.hepsiburada.api.repository;

import com.hepsiburada.api.model.BestsellerProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BestsellerProductRepository extends CrudRepository<BestsellerProduct, Integer> {

    @Query(
            value = "",
            nativeQuery = true
    )
    public List<?> getPersonalizedTenProduct();

}
