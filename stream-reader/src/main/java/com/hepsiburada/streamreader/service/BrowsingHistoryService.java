package com.hepsiburada.streamreader.service;

import com.hepsiburada.streamreader.model.BrowsingHistory;
import com.hepsiburada.streamreader.repository.BrowsingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrowsingHistoryService {

    private final BrowsingHistoryRepository repository;

    public void saveOrUpdate(BrowsingHistory history) {
        repository.save(history);
    }

}
