package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiSearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(value = "query", required = false) String query) {
        if (query == null || query.trim().isEmpty() || query.length() < 2) {
            return ResponseEntity.ok(List.of());
        }
        
        List<Product> results = productService.searchProducts(query, 5); 
        return ResponseEntity.ok(results);
    }
}