package com.danielrutten.springdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Stock {

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
    private Integer itemsInStock;

    // Prevent infinite recursion in JSON generation due to bidirectional relationship between Stock and Reservation
    @JsonManagedReference
    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "stock")
    private List<Reservation> reservations = Collections.emptyList();

    /**
     * Finalize the given reservation by decreasing the items in stock with the items reserved and removing the reservation.
     *
     * @param reservation The reservation to finalize.
     */
    public void finalizeReservation(Reservation reservation) {
        itemsInStock = itemsInStock - reservation.getItemsReserved();
        reservation.setStatus(ReservationStatus.CLOSED);
    }
}
