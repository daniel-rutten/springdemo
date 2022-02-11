package com.danielrutten.springdemo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Stock {

    @EmbeddedId
    private StockId stockId;

    @Column
    private int itemsInStock;

}
