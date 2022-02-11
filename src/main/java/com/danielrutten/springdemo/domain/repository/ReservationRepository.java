package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Iterable<Reservation> findByStoreId(Long storeId);

    Iterable<Reservation> findByStoreIdAndProductId(Long storeId, Long productId);
}
