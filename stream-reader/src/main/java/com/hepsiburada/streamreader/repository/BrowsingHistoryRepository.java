package com.hepsiburada.streamreader.repository;

import com.hepsiburada.streamreader.model.BrowsingHistory;
import com.hepsiburada.streamreader.model.serialize.BrowsingHistoryId;
import org.springframework.data.repository.CrudRepository;

public interface BrowsingHistoryRepository extends CrudRepository<BrowsingHistory, BrowsingHistoryId> {
}
