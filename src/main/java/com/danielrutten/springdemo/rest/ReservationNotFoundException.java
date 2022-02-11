package com.danielrutten.springdemo.rest;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long reservationId) {
        super(String.format("Could not find reservation for id %s", reservationId));
    }
}