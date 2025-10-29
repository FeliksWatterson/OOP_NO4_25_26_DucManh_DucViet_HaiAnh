package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;

import java.util.Optional;

public interface CustomerInterface {

    Customer registerCustomer(Customer customer);
    Customer findById(Long id);
    void updateCustomer(Customer customer);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> authenticateCustomer(String email, String rawPassword);

    boolean updatePassword(Long customerId, String oldPassword, String newPassword);
    void deleteCustomer(Long customerId);
}