package com.hepsiburada.api.controller;

import com.hepsiburada.api.service.BrowsingHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.is;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(BrowsingHistoryController.class)
public class BrowsingHistoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BrowsingHistoryService historyService;

    @Test
    public void givenProductList_whenTenViewedProduct_thenReturnJsonArray() throws Exception {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("user-id", 111);
        List<Integer> products = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        result.put("products", products);
        result.put("type", "personalized");


        Mockito.doReturn(result)
                .when(historyService).getViewedProduct(111);

        mvc.perform(get("/api/history/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", String.valueOf(111)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)))
                .andExpect(jsonPath("$.type", is("personalized")))
                .andExpect(jsonPath("$.products", is(products)));
    }

}
