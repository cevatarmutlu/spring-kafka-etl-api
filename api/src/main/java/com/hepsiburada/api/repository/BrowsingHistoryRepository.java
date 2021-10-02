package com.hepsiburada.api.repository;

import com.hepsiburada.api.model.BrowsingHistory;
import com.hepsiburada.api.model.serialize.BrowsingHistoryId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrowsingHistoryRepository extends CrudRepository<BrowsingHistory, BrowsingHistoryId> {

    @Query(
            value = "SELECT product_id FROM browsing_history\n" +
                    "WHERE user_id = :userId\n" +
                    "ORDER BY timestamp DESC",
            nativeQuery = true
    )
    public List<?> getTenViewedProduct(@Param("userId") int userId);

}
