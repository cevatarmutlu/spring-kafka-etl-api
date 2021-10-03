package com.hepsiburada.api.controller;

import com.hepsiburada.api.service.BestsellerProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BestsellerProductController.class)
public class BestsellerProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BestsellerProductService productService;

    @Test
    public void givenUserId_whenBestsellerProduct_thenReturnJsonArray() throws Exception {

        // User has history
        LinkedHashMap<String, Object> userHasHistoryProducts = new LinkedHashMap<>();
        userHasHistoryProducts.put("user-id", 111);
        userHasHistoryProducts.put("products", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        userHasHistoryProducts.put("type", "personalized");

        Mockito.doReturn(userHasHistoryProducts)
                .when(productService).getBestsellerProduct(111);

        mvc.perform(get("/api/bestseller/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(111)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.type", is("personalized")))
                .andExpect(jsonPath("$.products", is(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))));

        // User has not history
        LinkedHashMap<String, Object> userHasNotHistoryProducts = new LinkedHashMap<>();
        userHasNotHistoryProducts.put("user-id", 112);
        userHasNotHistoryProducts.put("products", List.of(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110));
        userHasNotHistoryProducts.put("type", "non-personalized");

        Mockito.doReturn(userHasNotHistoryProducts)
                .when(productService).getBestsellerProduct(112);

        mvc.perform(get("/api/bestseller/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(112)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.type", is("non-personalized")))
                .andExpect(jsonPath("$.products", is(List.of(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110))));

    }

}
