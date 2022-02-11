package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Product;
import com.danielrutten.springdemo.domain.entity.Stock;
import com.danielrutten.springdemo.domain.entity.Store;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.StockRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import com.danielrutten.springdemo.service.StockValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockValidationService stockValidationService;

    @GetMapping("/product/{productId}/stock")
    public Iterable<Stock> getStockForProduct(@PathVariable Long productId) {
        return stockRepository.findByProductId(productId);
    }

    @GetMapping("/store/{storeId}/stock")
    public Iterable<Stock> getStockForStore(@PathVariable Long storeId) {
        return stockRepository.findByStoreId(storeId);
    }

    @GetMapping("/store/{storeId}/product/{productId}/stock")
    public Stock getStockForStoreAndProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return stockRepository.findByStoreIdAndProductId(storeId, productId)
                .orElseThrow(() -> new StockNotFoundException(storeId, productId));
    }

    @PostMapping("/store/{storeId}/product/{productId}/stock")
    Stock createStock(@RequestBody StockDto stockDto, @PathVariable Long storeId, @PathVariable Long productId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException(storeId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Stock stock = Stock.builder()
                .store(store)
                .product(product)
                .itemsInStock(stockDto.getItemsInStock())
                .build();
        return stockRepository.save(stock);
    }

    @PutMapping("/store/{storeId}/product/{productId}/stock")
    Stock updateStock(@RequestBody StockDto stockDto, @PathVariable Long storeId, @PathVariable Long productId) {
        return stockRepository.findByStoreIdAndProductId(storeId, productId)
                .map(s -> {
                    s.setItemsInStock(stockDto.getItemsInStock());
                    stockValidationService.validate(s); // Check if sufficient stock remains for all reservations
                    return stockRepository.save(s);
                })
                .orElseGet(() -> createStock(stockDto, storeId, productId)); // If stock is not found, create stock instead
    }

    @DeleteMapping("/store/{storeId}/product/{productId}/stock")
    void deleteStock(@PathVariable Long storeId, @PathVariable Long productId) {
        stockRepository.findByStoreIdAndProductId(storeId, productId)
                .ifPresent(s -> stockRepository.delete(s));
    }

}
