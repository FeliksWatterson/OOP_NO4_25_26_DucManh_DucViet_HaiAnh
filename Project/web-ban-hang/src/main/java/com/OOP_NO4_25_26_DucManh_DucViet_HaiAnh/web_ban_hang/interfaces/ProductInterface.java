package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts();
    List<Product> findNewProducts(int limit); 
    List<Product> findBestSellingProducts(int limit); 
    List<Product> findHighlyRatedProducts(int limit); 
    List<Product> findProductsOnSale(int limit); 
}