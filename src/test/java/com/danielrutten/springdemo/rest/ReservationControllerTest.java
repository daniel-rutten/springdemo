package com.danielrutten.springdemo.rest;

import com.danielrutten.springdemo.domain.entity.Reservation;
import com.danielrutten.springdemo.domain.entity.ReservationStatus;
import com.danielrutten.springdemo.domain.entity.Stock;
import com.danielrutten.springdemo.domain.repository.ProductRepository;
import com.danielrutten.springdemo.domain.repository.ReservationRepository;
import com.danielrutten.springdemo.domain.repository.StockRepository;
import com.danielrutten.springdemo.domain.repository.StoreRepository;
import com.danielrutten.springdemo.service.StockValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.danielrutten.springdemo.rest.JsonTestUtil.toJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private StockRepository stockRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private StockValidationService stockValidationService;

    @Captor
    private ArgumentCaptor<Reservation> reservationArgumentCaptor;

    @Test
    void when_postReservation_then_reservationIsAdded() throws Exception {
        // given
        Stock stock = Stock.builder().id(1L).itemsInStock(117).build();
        when(stockRepository.findByStoreIdAndProductId(13L, 24L)).thenReturn(Optional.of(stock));

        // when+then
        mockMvc.perform(post("/stores/13/products/24/reservations")
                        .content(toJson(ReservationDto.builder().itemsReserved(99).status(ReservationStatus.OPEN).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(reservationRepository).save(reservationArgumentCaptor.capture());

        Reservation actual = reservationArgumentCaptor.getValue();
        assertThat(actual.getStock().getId(), equalTo(1L));
        assertThat(actual.getStatus(), equalTo(ReservationStatus.OPEN));
        assertThat(actual.getItemsReserved(), equalTo(99));
    }
}