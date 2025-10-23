package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob // Dùng @Lob cho các chuỗi dài như mô tả
    @Column(columnDefinition = "TEXT") // Định nghĩa kiểu cột trong DB là TEXT
    private String description;

    @Column(nullable = false)
    private double price;

    private Double originalPrice; // Giá gốc để hiển thị giảm giá (tùy chọn)

    private String category;

    private String imageUrl; // Ảnh chính

    private String hoverImageUrl; // Ảnh hiển thị khi di chuột (tùy chọn)

    private Integer stock; // Số lượng tồn kho

    // Thêm các trường khác nếu cần, ví dụ: rating, badges,...

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHoverImageUrl() {
        return hoverImageUrl;
    }

    public void setHoverImageUrl(String hoverImageUrl) {
        this.hoverImageUrl = hoverImageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}