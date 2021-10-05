package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BestsellerProductRepository;
import com.hepsiburada.api.repository.BrowsingHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BestsellerProductService {

    @Autowired
    private BrowsingHistoryRepository historyRepository;

    @Autowired
    private BestsellerProductRepository productRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BestsellerProductService.class);

    public boolean isUserHistoryExists(int userId) {
        return historyRepository.findByUserId(userId).size() != 0;
    }

    public LinkedHashMap<String, Object> getBestsellerProduct(int userId) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("user-id", userId);

        if (isUserHistoryExists(userId)) {
            List<?> products = productRepository.getBestsellerProductsIfUserHistoryExist(userId);
            LOGGER.info("user has history: recommend products {}", products);

            result.put("products", getCorrectProducts(products));
            result.put("type", "personalized");
            LOGGER.info("send response: {}", result);
            return result;
        }

        List<?> products = productRepository.getBestsellerProductsIfUserHistoryNotExist();
        LOGGER.info("user has not history: recommend products {}", products);
        result.put("products", getCorrectProducts(products));
        result.put("type", "non-personalized");
        LOGGER.info("send response: {}", result);
        return result;
    }

    private List<?> getCorrectProducts(List<?> products) {
        if (products.size() < 5) {
            LOGGER.info("products fewer than 5");
            return new ArrayList<>();
        }

        LOGGER.info("products bigger than 5");
        return products;
    }

}
