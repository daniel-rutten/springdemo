package com.danielrutten.springdemo.rest;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(Long storeId, Long productId) {
        super(String.format("Could not find stock for storeId %s and productId %s", storeId, productId));
    }
}