package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Reservation;
import com.danielrutten.springdemo.domain.entity.Stock;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.ReservationRepository;
import com.danielrutten.springdemo.domain.repository.StockRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import com.danielrutten.springdemo.service.StockValidationService;
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
    private StockRepository stockRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StockValidationService stockValidationService;

    @GetMapping("reservations/{reservationId}")
    public Optional<Reservation> getReservation(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @GetMapping("/stores/{storeId}/reservations")
    public Iterable<Reservation> getReservationForStore(@PathVariable Long storeId) {
        return reservationRepository.findByStockStoreId(storeId);
    }

    @GetMapping("/stores/{storeId}/products/{productId}/reservations")
    public Iterable<Reservation> getReservationForStoreAndProduct(@PathVariable Long storeId, @PathVariable Long productId) {
        return reservationRepository.findByStockStoreIdAndStockProductId(storeId, productId);
    }

    @PostMapping("/stores/{storeId}/products/{productId}/reservations")
    Reservation createReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long storeId, @PathVariable Long productId) {
        Stock stock = stockRepository.findByStoreIdAndProductId(storeId, productId)
                .orElseThrow(() -> new StockNotFoundException(storeId, productId));
        Reservation reservation = Reservation.builder()
                .stock(stock)
                .itemsReserved(reservationDto.getItemsReserved())
                .status(reservationDto.getStatus())
                .reservationDateTime(LocalDateTime.now())
                .build();
        stock.getReservations().add(reservation);
        stockValidationService.validate(stock); // Check if sufficient stock remains for all reservations
        return reservationRepository.save(reservation);
    }

    @PutMapping("/reservations/{reservationId}")
    Reservation updateReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(r -> {
                    r.setItemsReserved(reservationDto.getItemsReserved());
                    r.setStatus(reservationDto.getStatus());
                    stockValidationService.validate(r.getStock()); // Check if sufficient stock remains for all reservations

                    r.getStock().updateItemsInStock(r);
                    stockRepository.save(r.getStock());

                    return reservationRepository.save(r);
                })
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }

    @DeleteMapping("/reservations/{reservationId}")
    void deleteReservation(@PathVariable Long reservationId) {
        reservationRepository.findById(reservationId)
                .ifPresent(r -> {
                    r.getStock().getReservations().remove(r);
                    stockRepository.save(r.getStock());
                    reservationRepository.delete(r);
                });
    }

}
