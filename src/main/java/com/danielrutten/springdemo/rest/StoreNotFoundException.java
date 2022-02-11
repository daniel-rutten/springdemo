package com.danielrutten.springdemo.rest;

class StoreNotFoundException extends RuntimeException {

    StoreNotFoundException(Long storeId) {
        super(String.format("Could not find store with id %s", storeId));
    }
}