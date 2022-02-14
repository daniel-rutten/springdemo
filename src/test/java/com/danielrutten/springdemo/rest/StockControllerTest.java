package com.danielrutten.springdemo.rest;

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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StockController.class)
class StockControllerTest {

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
    private ArgumentCaptor<Stock> stockArgumentCaptor;

    @Test
    void when_putStock_then_stockIsUpdated() throws Exception {
        // given
        Stock stock = Stock.builder().itemsInStock(1).build();
        when(stockRepository.findByStoreIdAndProductId(11L, 73L)).thenReturn(Optional.of(stock));
        when(stockRepository.save(stockArgumentCaptor.capture())).thenReturn(stock);

        // when+then
        mockMvc.perform(put("/stores/11/products/73/stocks")
                        .content(toJson(StockDto.builder().itemsInStock(128).build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Stock actual = stockArgumentCaptor.getValue();
        assertThat(actual.getItemsInStock(), equalTo(128));
    }
}