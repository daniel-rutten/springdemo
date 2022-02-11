package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Product;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productRepository.findById(id);
    }
}
