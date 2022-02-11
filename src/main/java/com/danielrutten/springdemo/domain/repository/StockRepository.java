package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    Iterable<Stock> findByStockIdProductId(Long productId);

    Iterable<Stock> findByStockIdStoreId(Long storeId);
}
