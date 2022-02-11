package com.danielrutten.springdemo.domain.repository;

import com.danielrutten.springdemo.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStockStoreId(Long storeId);

    List<Reservation> findByStockStoreIdAndStockProductId(Long storeId, Long productId);
}
