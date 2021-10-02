package com.hepsiburada.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bestseller_product")
@Getter
@Setter
@ToString
public class BestsellerProduct {

    @Id
    private int id;

    private int userId;

    private int categoryId;

    private int productId;
}
