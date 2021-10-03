package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BrowsingHistoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class BrowsingHistoryServiceTest {

    @TestConfiguration
    static class BrowsingHistoryServiceTestContextConfiguration {
        @Bean
        public BrowsingHistoryService movieService() {
            return new BrowsingHistoryService() {
                // implement methods
            };
        }
    }

    @Autowired
    private BrowsingHistoryService historyService;

    @MockBean
    private BrowsingHistoryRepository historyRepository;

    private List<?> dummyProducts;

    @Before
    public void setUp() {
        dummyProducts = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Mockito.doReturn(dummyProducts).when(historyRepository).getTenViewedProduct(111);

    }


    @Test
    public void getTenViewedProductTest() {

        List<?> products = historyService.getTenViewedProduct(111);

        Assert.assertEquals(products, dummyProducts);
        Assert.assertEquals(products.get(0), dummyProducts.get(0));
    }

}
