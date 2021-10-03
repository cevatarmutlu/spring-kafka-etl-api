package com.hepsiburada.api.model;


import com.hepsiburada.api.model.serialize.BrowsingHistoryId;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "browsing_history")
@IdClass(BrowsingHistoryId.class)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BrowsingHistory {

    @Id
    private int userId;
    @Id
    private int productId;
    private int categoryId;
    @Id
    private Timestamp timestamp;

}
