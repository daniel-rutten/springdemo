package com.danielrutten.springdemo.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


    @EmbeddedId
    private StockId stockId;

    @Column
    private int itemsInStock;

}
