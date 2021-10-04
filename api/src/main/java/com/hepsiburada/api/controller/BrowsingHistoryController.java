package com.hepsiburada.api.controller;

import com.hepsiburada.api.service.BrowsingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("api/history")
public class BrowsingHistoryController {

    @Autowired
    private BrowsingHistoryService historyService;

    @GetMapping("/products")
    public LinkedHashMap<String, Object> getViewedProduct(@RequestParam Integer userId) {
        return historyService.getViewedProduct(userId);
    }

    @DeleteMapping("/delete")
    public LinkedHashMap<String, Object> deleteProductFromHistory(@RequestParam int userId, @RequestParam int productId) {
        return historyService.deleteProductFromHistory(userId, productId);
    }

}
