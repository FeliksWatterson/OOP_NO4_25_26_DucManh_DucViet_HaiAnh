package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.service;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Customer;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> searchByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Customer> searchByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> searchByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}
