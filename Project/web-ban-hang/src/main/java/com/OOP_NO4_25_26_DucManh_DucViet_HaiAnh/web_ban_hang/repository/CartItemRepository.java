package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.CartItem;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCustomer(Customer customer);

    Optional<CartItem> findByCustomerAndProduct(Customer customer, Product product);

    @Modifying 
    @Transactional 
    @Query("DELETE FROM CartItem ci WHERE ci.customer = ?1 AND ci.product = ?2")
    void deleteByCustomerAndProduct(Customer customer, Product product);

    @Modifying
    @Transactional
    void deleteByCustomer(Customer customer);

    @Query("SELECT SUM(ci.quantity) FROM CartItem ci WHERE ci.customer = ?1")
    Integer sumQuantityByCustomer(Customer customer);
}