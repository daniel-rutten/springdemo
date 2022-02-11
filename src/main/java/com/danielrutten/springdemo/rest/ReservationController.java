package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Product;
import com.danielrutten.springdemo.domain.entity.Reservation;
import com.danielrutten.springdemo.domain.entity.Store;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.ReservationRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class ReservationController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("reservations/{reservationId}")
    public Optional<Reservation> getReservation(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @GetMapping("/store/{storeId}/reservations")
    public Iterable<Reservation> getReservationForStore(@PathVariable Long storeId) {
        return reservationRepository.findByStoreId(storeId);
    }

    @GetMapping("/store/{storeId}/product/{productId}/reservations")
    public Iterable<Reservation> getReservationForStoreAndProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return reservationRepository.findByStoreIdAndProductId(storeId, productId);
    }

    @PostMapping("/store/{storeId}/product/{productId}/reservations")
    Reservation createReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long storeId, @PathVariable Long productId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException(storeId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Reservation reservation = Reservation.builder()
                .store(store)
                .product(product)
                .itemsReserved(reservationDto.getItemsReserved())
                .status(reservationDto.getStatus())
                .reservationDateTime(LocalDateTime.now())
                .build();
        return reservationRepository.save(reservation);
    }

    @PutMapping("/reservations/{reservationId}")
    Reservation updateReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(r -> {
                    r.setItemsReserved(reservationDto.getItemsReserved());
                    r.setStatus(reservationDto.getStatus());
                    return reservationRepository.save(r);
                })
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }

    @DeleteMapping("/reservations/{reservationId}")
    void deleteReservation(@PathVariable Long reservationId) {
        reservationRepository.findById(reservationId)
                .ifPresent(r -> reservationRepository.delete(r));
    }

}
