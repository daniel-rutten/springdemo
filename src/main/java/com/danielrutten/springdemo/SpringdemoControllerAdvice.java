package com.danielrutten.springdemo;

import com.danielrutten.springdemo.rest.ProductNotFoundException;
import com.danielrutten.springdemo.rest.ReservationNotFoundException;
import com.danielrutten.springdemo.rest.StockNotFoundException;
import com.danielrutten.springdemo.rest.StoreNotFoundException;
import com.danielrutten.springdemo.service.StockValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SpringdemoControllerAdvice {

    @ResponseBody
    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String storeNotFoundExceptionHandler(StoreNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundExceptionHandler(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stockNotFoundExceptionHandler(StockNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reservationNotFoundExceptionHandler(ReservationNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StockValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String stockValidationExceptionHandler(StockValidationException ex) {
        return ex.getMessage();
    }

}
