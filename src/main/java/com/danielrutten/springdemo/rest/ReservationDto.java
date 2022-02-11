package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ReservationDto {

    private Integer itemsReserved;
    private ReservationStatus status;
}
