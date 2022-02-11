package com.danielrutten.springdemo.service;

public class StockValidationException extends RuntimeException {

    public StockValidationException(Integer itemsInStock, Integer itemsReserved) {
        super(String.format("Items reserved (%s) exceeds items in stock (%s)!", itemsReserved, itemsInStock));
    }
}