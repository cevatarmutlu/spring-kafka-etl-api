package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BestsellerProductRepository;
import com.hepsiburada.api.repository.BrowsingHistoryRepository;
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

    public boolean isUserHistoryExists(int userId) {
        return historyRepository.findByUserId(userId).size() != 0;
    }

    public LinkedHashMap<String, Object> getBestsellerProduct(int userId) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("user-id", userId);

        if (isUserHistoryExists(userId)) {
            List<?> products = productRepository.getBestsellerProductsIfUserHistoryExist(userId);

            result.put("products", getCorrectProducts(products));
            result.put("type", "personalized");
            return result;
        }

        List<?> products = productRepository.getBestsellerProductsIfUserHistoryNotExist();
        result.put("products", getCorrectProducts(products));
        result.put("type", "non-personalized");
        return result;
    }

    private List<?> getCorrectProducts(List<?> products) {
        if (products.size() < 5)
            return new ArrayList<>();
        return products;
    }

}
