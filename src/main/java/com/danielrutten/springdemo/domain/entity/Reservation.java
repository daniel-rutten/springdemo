package com.danielrutten.springdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "RESERVATION")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    public static final Duration EXPIRATION_TIME = Duration.ofMinutes(5);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Prevent infinite recursion in JSON generation due to bidirectional relationship between Stock and Reservation
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

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
