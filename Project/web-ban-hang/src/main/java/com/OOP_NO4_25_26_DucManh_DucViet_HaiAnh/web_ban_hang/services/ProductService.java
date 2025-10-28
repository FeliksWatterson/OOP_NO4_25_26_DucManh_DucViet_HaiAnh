package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.ProductInterface; // Assuming you have this interface
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface { 

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsBySection(String section, int limit) {
        Pageable pageable = PageRequest.of(0, limit); 
        return productRepository.findBySection(section, pageable);
    }
     public List<Product> findProductsBySection(String section) {
        return productRepository.findBySection(section); 
    }

     @Override
     public List<Product> findNewProducts(int limit) {
         Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "id")); 
         return productRepository.findBySection("new", pageable);
     }

     @Override
     public List<Product> findBestSellingProducts(int limit) {
         Pageable pageable = PageRequest.of(0, limit);
         return productRepository.findBySection("mostBought", pageable);
     }

     @Override
     public List<Product> findHighlyRatedProducts(int limit) {
         Pageable pageable = PageRequest.of(0, limit);
         return productRepository.findBySection("topRated", pageable);
     }

     @Override
     public List<Product> findProductsOnSale(int limit) {
         Pageable pageable = PageRequest.of(0, limit);
         return productRepository.findBySection("flashSale", pageable);
     }

     public List<Product> findSidebarBestSellers(int limit) {
         Pageable pageable = PageRequest.of(0, limit);
         return productRepository.findBySection("bestSeller", pageable);
     }

     public List<Product> findSuggestedProducts() {
         return productRepository.findBySection("suggested");
     }
}