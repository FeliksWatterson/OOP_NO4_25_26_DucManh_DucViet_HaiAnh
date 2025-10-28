package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}