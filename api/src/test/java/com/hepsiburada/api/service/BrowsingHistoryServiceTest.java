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

import java.util.LinkedHashMap;
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


    @Before
    public void setUp() {
        List<?> ifExistFiveProducts = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Mockito.doReturn(ifExistFiveProducts).when(historyRepository).getViewedProduct(111);

        List<?> ifNotExistFiveProducts = List.of();
        Mockito.doReturn(ifNotExistFiveProducts).when(historyRepository).getViewedProduct(112);

    }


    @Test
    public void getTenViewedProductTest() {

        // if exist five products
        LinkedHashMap<String, Object> result = historyService.getViewedProduct(111);

        LinkedHashMap<String, Object> expected_result = new LinkedHashMap<>();
        expected_result.put("user-id", 111);
        expected_result.put("products", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        expected_result.put("type", "personalized");

        Assert.assertEquals(expected_result, result);


        // if not exist five products
        result = historyService.getViewedProduct(112);

        expected_result = new LinkedHashMap<>();
        expected_result.put("user-id", 112);
        expected_result.put("products", List.of());
        expected_result.put("type", "personalized");

        Assert.assertEquals(expected_result, result);



    }

}
