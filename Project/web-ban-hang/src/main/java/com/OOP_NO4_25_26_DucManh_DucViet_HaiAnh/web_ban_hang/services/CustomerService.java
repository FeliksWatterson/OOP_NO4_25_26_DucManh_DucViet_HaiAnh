package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.CustomerInterface;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer; 
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService implements CustomerInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại, vui lòng đăng nhập để tiếp tục.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> authenticateCustomer(String email, String rawPassword) {
        Optional<Customer> customerOpt = findCustomerByEmail(email);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.getPassword().equals(rawPassword)) {
                return customerOpt;
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public Customer findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void updateCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }
}