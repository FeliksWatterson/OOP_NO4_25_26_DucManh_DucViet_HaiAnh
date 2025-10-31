package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySection(String section, Pageable pageable);
    List<Product> findBySection(String section);
    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}