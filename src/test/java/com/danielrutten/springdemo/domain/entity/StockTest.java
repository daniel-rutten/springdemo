package com.danielrutten.springdemo.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit-test for the class {@link Stock}
 */
class StockTest {

    @Test
    void when_reservationIsOpen_then_itemsInStockUnchanged() {
        // given
        Reservation reservation = Reservation.builder().itemsReserved(4).status(ReservationStatus.OPEN).build();
        Stock stock = Stock.builder().itemsInStock(10).reservations(Arrays.asList(reservation)).build();

        // when
        stock.updateItemsInStock(reservation);

        // then
        assertThat(reservation.getStatus(), equalTo(ReservationStatus.OPEN));
        assertThat(stock.getItemsInStock(), equalTo(10));
    }

    @Test
    void when_reservationIsPickedUp_then_itemsInStockDecreasedAndReservationClosed() {
        // given
        Reservation reservation = Reservation.builder().itemsReserved(7).status(ReservationStatus.PICKED_UP).build();
        Stock stock = Stock.builder().itemsInStock(10).reservations(Arrays.asList(reservation)).build();

        // when
        stock.updateItemsInStock(reservation);

        // then
        assertThat(reservation.getStatus(), equalTo(ReservationStatus.CLOSED));
        assertThat(stock.getItemsInStock(), equalTo(3));
    }

}