package com.hepsiburada.streamreader.repository.remote;

import com.hepsiburada.streamreader.model.remote.BrowsingHistory;
import com.hepsiburada.streamreader.model.remote.serialize.BrowsingHistoryId;
import org.springframework.data.repository.CrudRepository;

public interface BrowsingHistoryRepository extends CrudRepository<BrowsingHistory, BrowsingHistoryId> {
}
