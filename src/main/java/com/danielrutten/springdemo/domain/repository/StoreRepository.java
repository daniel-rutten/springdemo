package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
