package com.danielrutten.springdemo.rest;

class ReservationNotFoundException extends RuntimeException {

    ReservationNotFoundException(Long reservationId) {
        super(String.format("Could not find reservation for id %s", reservationId));
    }
}