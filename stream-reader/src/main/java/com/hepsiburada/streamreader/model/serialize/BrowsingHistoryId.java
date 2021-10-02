package com.hepsiburada.streamreader.model.serialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BrowsingHistoryId implements Serializable {

    private final int userId;
    private final int productId;
    private final Timestamp timestamp;

}
