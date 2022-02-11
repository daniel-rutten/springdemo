package com.danielrutten.springdemo.service;

import com.danielrutten.springdemo.domain.entity.Reservation;
import com.danielrutten.springdemo.domain.entity.ReservationStatus;
import com.danielrutten.springdemo.domain.entity.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit-test for the class {@link StockValidationService}
 */
@ExtendWith(MockitoExtension.class)
class StockValidationServiceTest {

    @InjectMocks
    private StockValidationService stockValidationService;

    @Test
    void when_reservationIsClosed_then_valid() {
        // given
        Reservation reservation = Reservation.builder()
                .itemsReserved(100)
                .status(ReservationStatus.CLOSED)
                .reservationDateTime(LocalDateTime.now())
                .build();
        Stock stock = Stock.builder().itemsInStock(1).reservations(Collections.singletonList(reservation)).build();

        // when
        assertDoesNotThrow(() -> stockValidationService.validate(stock));
    }

    @Test
    void when_reservationIsExpired_then_valid() {
        // given
        Reservation reservation = Reservation.builder()
                .itemsReserved(100)
                .status(ReservationStatus.OPEN)
                .reservationDateTime(LocalDateTime.now().minus(Reservation.EXPIRATION_TIME).minusSeconds(1))
                .build();
        Stock stock = Stock.builder().itemsInStock(1).reservations(Collections.singletonList(reservation)).build();

        // when
        assertDoesNotThrow(() -> stockValidationService.validate(stock));
    }

    @Test
    void when_reservationIsOpenButSufficientItemsInStock_then_valid() {
        // given
        Reservation reservation = Reservation.builder()
                .itemsReserved(100)
                .status(ReservationStatus.OPEN)
                .reservationDateTime(LocalDateTime.now())
                .build();
        Stock stock = Stock.builder().itemsInStock(100).reservations(Collections.singletonList(reservation)).build();

        // when
        assertDoesNotThrow(() -> stockValidationService.validate(stock));
    }

    @Test
    void when_reservationIsActiveWithInsufficientItemsInStock_then_exception() {
        // given
        Reservation reservation = Reservation.builder()
                .itemsReserved(100)
                .status(ReservationStatus.OPEN)
                .reservationDateTime(LocalDateTime.now())
                .build();
        Stock stock = Stock.builder().itemsInStock(99).reservations(Collections.singletonList(reservation)).build();

        // when
        StockValidationException e = assertThrows(StockValidationException.class, () -> stockValidationService.validate(stock));
        assertThat(e.getMessage(), equalTo("Items reserved (100) exceeds items in stock (99)!"));
    }

}