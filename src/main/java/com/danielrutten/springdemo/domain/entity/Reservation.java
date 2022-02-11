package com.danielrutten.springdemo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    public static final Duration EXPIRATION_TIME = Duration.ofMinutes(5);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private Integer itemsReserved;

    @Column
    private LocalDateTime reservationDateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    /**
     * A reservation is active when the status is OPEN (and not CANCELLED or FINALIZED)
     * and the reservation time is not expired.
     *
     * @return True, if the reservation is still active. False, otherwise.
     */
    public boolean isActive() {
        return ReservationStatus.OPEN.equals(status)
                && LocalDateTime.now().isBefore(reservationDateTime.plus(EXPIRATION_TIME));
    }
}
