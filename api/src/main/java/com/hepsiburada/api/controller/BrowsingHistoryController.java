package com.hepsiburada.api.controller;

import com.hepsiburada.api.service.BrowsingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("api/history")
public class BrowsingHistoryController {

    @Autowired
    private BrowsingHistoryService historyService;

    @GetMapping("/tenproduct")
    public LinkedHashMap<String, Object> getTenViewedProduct(@RequestParam Integer userid) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>(); // HashMap order is wrong.
        result.put("user-id", userid);
        result.put("products", historyService.getTenViewedProduct(userid));
        result.put("type", "personalized");
        return result;
    }

    @DeleteMapping("/delete")
    public void deleteProductFromHistory(@RequestParam int userId, @RequestParam int productId) {
        historyService.deleteProductFromHistory(userId, productId);
    }

}
