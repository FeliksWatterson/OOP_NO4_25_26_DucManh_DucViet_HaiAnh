package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Address;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AddressInterface {
    List<Address> findAddressesByCustomer(Customer customer);
    Optional<Address> findDefaultAddressByCustomer(Customer customer);
    Address saveAddress(Address address);
    Optional<Address> findByIdAndCustomer(Long id, Customer customer);
    void deleteAddress(Long id, Customer customer);
    void setDefaultAddress(Long id, Customer customer);
    long countAddressesByCustomer(Customer customer);
}