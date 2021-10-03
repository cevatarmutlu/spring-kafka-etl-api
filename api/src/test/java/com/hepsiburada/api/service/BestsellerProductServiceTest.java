package com.hepsiburada.api.service;

import com.hepsiburada.api.model.BrowsingHistory;
import com.hepsiburada.api.repository.BestsellerProductRepository;
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

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
public class BestsellerProductServiceTest {

    @TestConfiguration
    static class BestsellerProductServiceTestContextConfiguration {
        @Bean
        public BestsellerProductService movieService() {
            return new BestsellerProductService() {
                // implement methods
            };
        }
    }

    @Autowired
    private BestsellerProductService productService;

    @MockBean
    private BestsellerProductRepository productRepository;

    @MockBean
    private BrowsingHistoryRepository historyRepository;


    @Before
    public void setUp() {
        Mockito.when(historyRepository.findByUserId(111))
                .thenReturn(
                        List.of(
                                new BrowsingHistory(111, 1, 100, new Timestamp(System.currentTimeMillis())),
                                new BrowsingHistory(111, 2, 101, new Timestamp(System.currentTimeMillis())),
                                new BrowsingHistory(111, 3, 102, new Timestamp(System.currentTimeMillis())),
                                new BrowsingHistory(111, 4, 103, new Timestamp(System.currentTimeMillis()))
                        )
                );

        Mockito.when(historyRepository.findByUserId(112))
                .thenReturn(List.of());



        Mockito.doReturn(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).when(productRepository).getBestsellerProductsIfUserHistoryExist(111);
        Mockito.doReturn(List.of(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110)).when(productRepository).getBestsellerProductsIfUserHistoryNotExist();
    }


    @Test
    public void existUserHistoryTest_whenUserHasHistoryOrNot() {
        Assert.assertTrue(productService.existUserHistory(111));
        Assert.assertFalse(productService.existUserHistory(112));
    }

    @Test
    public void getBestsellerProductTest_whenUserHasHistoryOrNot() {

        // User has history
        LinkedHashMap<String, Object> userHasHistoryProducts = new LinkedHashMap<>();
        userHasHistoryProducts.put("user-id", 111);
        userHasHistoryProducts.put("products", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        userHasHistoryProducts.put("type", "personalized");

        Assert.assertEquals(productService.getBestsellerProduct(111), userHasHistoryProducts);


        // User has not history
        LinkedHashMap<String, Object> userHasNotHistoryProducts = new LinkedHashMap<>();
        userHasNotHistoryProducts.put("user-id", 112);
        userHasNotHistoryProducts.put("products", List.of(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110));
        userHasNotHistoryProducts.put("type", "non-personalized");

        Assert.assertEquals(productService.getBestsellerProduct(112), userHasNotHistoryProducts);


    }

}
