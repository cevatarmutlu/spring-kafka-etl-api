package com.hepsiburada.streamreader.service;

import com.hepsiburada.streamreader.model.Product;
import com.hepsiburada.streamreader.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<?> getCategoryId(String productId) {
        return repository.getCategoryId(productId);
    }
}
