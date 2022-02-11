package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByProductId(Long productId);

    List<Stock> findByStoreId(Long storeId);

    Optional<Stock> findByStoreIdAndProductId(Long storeId, Long productId);
}
