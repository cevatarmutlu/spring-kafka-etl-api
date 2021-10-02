package com.hepsiburada.api.controller;

import com.hepsiburada.api.service.BestsellerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("api/bestseller")
public class BestsellerProductController {

    @Autowired
    private BestsellerProductService productService;

    @GetMapping("/products")
    public LinkedHashMap<String, Object> products(int userId) {
        return productService.getBestsellerProduct(userId);
    }

}
