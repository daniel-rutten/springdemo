package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Stock;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.StockRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{productId}/stocks")
    public Iterable<Stock> getStockForProduct(@PathVariable Long productId) {
        return stockRepository.findByStockIdProductId(productId);
    }

    @GetMapping("/store/{storeId}/stocks")
    public Iterable<Stock> getStockForStore(@PathVariable Long storeId) {
        return stockRepository.findByStockIdStoreId(storeId);
    }

}
