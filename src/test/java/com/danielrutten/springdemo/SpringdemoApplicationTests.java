package com.danielrutten.springdemo;

import com.danielrutten.springdemo.rest.ProductController;
import com.danielrutten.springdemo.rest.StockController;
import com.danielrutten.springdemo.rest.StoreController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    private StoreController storeController;

    @Autowired
    private ProductController productController;

    @Autowired
    private StockController stockController;

    @Test
    void contextLoads() {
        assertThat(storeController, is(notNullValue()));
        assertThat(productController, is(notNullValue()));
        assertThat(stockController, is(notNullValue()));
    }

}
