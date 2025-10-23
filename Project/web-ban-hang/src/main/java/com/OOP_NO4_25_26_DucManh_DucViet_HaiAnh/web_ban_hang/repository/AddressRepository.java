package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Address;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomer(Customer customer);
    Optional<Address> findByCustomerAndIsDefaultTrue(Customer customer);
    long countByCustomer(Customer customer);
}