package com.hepsiburada.api.repository;

import com.hepsiburada.api.model.BestsellerProduct;
import com.hepsiburada.api.model.BrowsingHistory;
import com.hepsiburada.api.model.serialize.BrowsingHistoryId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrowsingHistoryRepository extends CrudRepository<BrowsingHistory, BrowsingHistoryId> {

    @Query(
            value = "SELECT product_id FROM browsing_history\n" +
                    "WHERE user_id = :userId\n" +
                    "ORDER BY timestamp DESC\n" +
                    "LIMIT 10",
            nativeQuery = true
    )
    List<?> getViewedProduct(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM browsing_history\n" +
                    "WHERE user_id = :userId AND product_id = :productId",
            nativeQuery = true
    )
    int deleteProduct(@Param("userId") int userId, @Param("productId") int productId);


    List<BrowsingHistory> findByUserId(int userId);
}
