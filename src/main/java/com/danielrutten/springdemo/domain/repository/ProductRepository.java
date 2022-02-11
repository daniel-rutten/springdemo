package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
