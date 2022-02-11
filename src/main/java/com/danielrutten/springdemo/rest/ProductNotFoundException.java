package com.danielrutten.springdemo.rest;

class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(Long productId) {
        super(String.format("Could not find product with id %s", productId));
    }
}