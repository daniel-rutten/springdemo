package com.danielrutten.springdemo.rest;

class StockNotFoundException extends RuntimeException {

    StockNotFoundException(Long storeId, Long productId) {
        super(String.format("Could not find stock for storeId %s and productId %s", storeId, productId));
    }
}