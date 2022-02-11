package com.danielrutten.springdemo.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.danielrutten.springdemo.domain.entity.Reservation.EXPIRATION_TIME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit-test for the class {@link Reservation}
 */
class ReservationTest {

    @Test
    void when_statusIsOpenAndNotExpired_then_reservationIsActive() {
        // given
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OPEN)
                .reservationDateTime(LocalDateTime.now().minus(EXPIRATION_TIME).plusSeconds(1))
                .build();

        // when+then
        assertThat(reservation.isActive(), equalTo(true));
    }

    @Test
    void when_statusIsCancelledAndNotExpired_then_reservationIsNotActive() {
        // given
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.CANCELLED)
                .reservationDateTime(LocalDateTime.now().minus(EXPIRATION_TIME).plusSeconds(1))
                .build();

        // when+then
        assertThat(reservation.isActive(), equalTo(false));
    }

    @Test
    void when_statusIsFinalizedAndNotExpired_then_reservationIsNotActive() {
        // given
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.FINALIZED)
                .reservationDateTime(LocalDateTime.now().minus(EXPIRATION_TIME).plusSeconds(1))
                .build();

        // when+then
        assertThat(reservation.isActive(), equalTo(false));
    }

    @Test
    void when_statusIsOpenAndExpired_then_reservationIsNotActive() {
        // given
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OPEN)
                .reservationDateTime(LocalDateTime.now().minus(EXPIRATION_TIME))
                .build();

        // when+then
        assertThat(reservation.isActive(), equalTo(false));
    }

}