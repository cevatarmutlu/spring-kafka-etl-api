package com.hepsiburada.api.service;

import com.hepsiburada.api.repository.BrowsingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryRepository historyRepository;

    public List<?> getTenViewedProduct(int userId) {
        List<?> products = historyRepository.getTenViewedProduct(userId);
        if (products.size() < 3) {
            return new ArrayList<>();
        }
        return products;
    }

    public void deleteProductFromHistory(int userId, int productId) {
        historyRepository.deleteProduct(userId, productId);
    }


}
