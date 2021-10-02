package com.hepsiburada.streamreader.model.serialize;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BrowsingHistoryId implements Serializable {

    private int userId;
    private int productId;
    private Timestamp timestamp;

}
