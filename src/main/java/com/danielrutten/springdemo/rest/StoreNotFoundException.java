package com.danielrutten.springdemo.rest;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(Long storeId) {
        super(String.format("Could not find store with id %s", storeId));
    }
}