package com.hepsiburada.streamreader.generator;

import com.hepsiburada.streamreader.model.BrowsingHistory;
import com.hepsiburada.streamreader.model.Event;
import com.hepsiburada.streamreader.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
public class BrowsingHistoryGenerator {

    private final Event event;
    private final ProductService service;

    private int getUserId() {
        return Integer.parseInt(
                this.event.getUserid()
                        .split("-")[1]
        );
    }

    private int getProductId() {
        return Integer.parseInt(
                this.event.getProperties().getProductid()
                        .split("-")[1]
        );
    }

    private int getCategoryId() {
        return Integer.parseInt(
                ((String) service.getCategoryId(this.event.getProperties().getProductid()).get(0))
                        .split("-")[1]
        );
    }

    private Timestamp getTimestamp() {
        return new Timestamp(this.event.getTimestamp());
    }

    public BrowsingHistory generate() {
        BrowsingHistory history = new BrowsingHistory();
        history.setUserId(getUserId());
        history.setProductId(getProductId());
        history.setCategoryId(getCategoryId());
        history.setTimestamp(getTimestamp());

        return history;
    }
}
