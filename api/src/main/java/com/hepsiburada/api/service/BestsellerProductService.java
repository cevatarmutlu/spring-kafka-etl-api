package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BestsellerProductRepository;
import com.hepsiburada.api.repository.BrowsingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BestsellerProductService {

    @Autowired
    private BrowsingHistoryRepository historyRepository;

    @Autowired
    private BestsellerProductRepository productRepository;

    public boolean existUserHistory(int userId) {
        return historyRepository.findByUserId(userId).size() != 0;
    }

    public LinkedHashMap<String, Object> getBestsellerProduct(int userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("user-id", userId);
        if (existUserHistory(userId)) {
            result.put("products", productRepository.getBestsellerProductsIfUserHistoryExist(userId));
            result.put("type", "personalized");
            return result;
        }

        result.put("products", productRepository.getBestsellerProductsIfUserHistoryNotExist());
        result.put("type", "non-personalized");
        return result;
    }

}
