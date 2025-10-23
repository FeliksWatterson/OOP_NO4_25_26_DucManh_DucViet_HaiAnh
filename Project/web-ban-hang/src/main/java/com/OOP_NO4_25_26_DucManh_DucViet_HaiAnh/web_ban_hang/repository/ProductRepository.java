package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository đã cung cấp sẵn các phương thức cơ bản như findById(), findAll(), save(), deleteById(),...
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần sau này.
    // Ví dụ: List<Product> findByCategory(String category);
}