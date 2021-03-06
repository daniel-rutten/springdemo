package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Store;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public Iterable<Store> getStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/stores/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(id));
    }
}
