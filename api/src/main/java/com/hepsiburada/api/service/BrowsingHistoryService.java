package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BrowsingHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryRepository historyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowsingHistoryService.class);

    public LinkedHashMap<String, Object> getViewedProduct(int userId) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>(); // HashMap order is wrong.
        result.put("user-id", userId);

        List<?> products = historyRepository.getViewedProduct(userId);
        LOGGER.info("fetch data from database: {}", products);

        if (products.size() < 5)
            result.put("products", new ArrayList<>());
        else
            result.put("products", products);

        result.put("type", "personalized");

        LOGGER.info("send response: {}", result);
        return result;
    }

    public LinkedHashMap<String, Object> deleteProductFromHistory(int userId, int productId) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("user-id", userId);
        result.put("product-id", productId);
        result.put("success", historyRepository.deleteProduct(userId, productId) != 0);

        return result;
    }


}
