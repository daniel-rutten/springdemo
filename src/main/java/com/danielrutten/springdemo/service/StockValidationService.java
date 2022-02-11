package com.danielrutten.springdemo.service;

import com.danielrutten.springdemo.domain.entity.Reservation;
import com.danielrutten.springdemo.domain.entity.Stock;
import org.springframework.stereotype.Service;

/**
 * Validates the current state of the stock of a product in a store by ensuring there is
 * enough stock to satisfy all active reservations. All closed or expired reservations
 * are ignored.
 */
@Service
public class StockValidationService {

    public void validate(Stock stock) {
        Integer totalItemsReserved = stock.getReservations()
                .stream()
                .filter(Reservation::isActive) // Only count the active reservations
                .map(Reservation::getItemsReserved)
                .mapToInt(Integer::intValue)
                .sum();
        if (totalItemsReserved > stock.getItemsInStock()) {
            throw new StockValidationException(stock.getItemsInStock(), totalItemsReserved);
        }
    }
}
