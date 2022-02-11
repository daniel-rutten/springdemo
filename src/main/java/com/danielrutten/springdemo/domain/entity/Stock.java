package com.danielrutten.springdemo.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @EmbeddedId
    private StockId stockId;

    @Column
    private Integer itemsInStock;

}
