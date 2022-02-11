package com.danielrutten.springdemo.rest;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super(String.format("Could not find product with id %s", productId));
    }
}