package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Product;
import com.danielrutten.springdemo.domain.entity.Stock;
import com.danielrutten.springdemo.domain.entity.StockId;
import com.danielrutten.springdemo.domain.entity.Store;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.StockRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
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

    @GetMapping("/product/{productId}/stock")
    public Iterable<Stock> getStockForProduct(@PathVariable Long productId) {
        return stockRepository.findByStockIdProductId(productId);
    }

    @GetMapping("/store/{storeId}/stock")
    public Iterable<Stock> getStockForStore(@PathVariable Long storeId) {
        return stockRepository.findByStockIdStoreId(storeId);
    }

    @GetMapping("/store/{storeId}/product/{productId}/stock")
    public StockDto getStockForStoreAndProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        Integer itemsInStock = stockRepository.findByStockIdStoreIdAndStockIdProductId(storeId, productId)
                .map(Stock::getItemsInStock)
                .orElse(0);
        return StockDto.builder().itemsInStock(itemsInStock).build();
    }

    @PostMapping("/store/{storeId}/product/{productId}/stock")
    Stock createStock(@RequestBody StockDto stockDto, @PathVariable Long storeId, @PathVariable Long productId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException(storeId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Stock stock = Stock.builder()
                .stockId(StockId.builder().store(store).product(product).build())
                .itemsInStock(stockDto.getItemsInStock())
                .build();
        return stockRepository.save(stock);
    }

    @PutMapping("/store/{storeId}/product/{productId}/stock")
    Stock updateStock(@RequestBody StockDto stockDto, @PathVariable Long storeId, @PathVariable Long productId) {
        return stockRepository.findByStockIdStoreIdAndStockIdProductId(storeId, productId)
                .map(s -> {
                    s.setItemsInStock(stockDto.getItemsInStock());
                    return stockRepository.save(s);
                })
                .orElseGet(() -> createStock(stockDto, storeId, productId)); // If stock is not found, create stock instead
    }

    @DeleteMapping("/store/{storeId}/product/{productId}/stock")
    void deleteStock(@PathVariable Long storeId, @PathVariable Long productId) {
        stockRepository.findByStockIdStoreIdAndStockIdProductId(storeId, productId)
                .ifPresent(s -> stockRepository.delete(s));
    }

}
