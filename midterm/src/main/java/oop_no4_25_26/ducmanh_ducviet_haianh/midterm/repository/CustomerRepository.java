package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.repository;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Customer;
import java.util.*;

public class CustomerRepository {
    private final Map<Long, Customer> customerMap = new HashMap<>();
    private long currentId = 1;

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(currentId++);
        }
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerMap.get(id));
    }

    public void deleteById(Long id) {
        customerMap.remove(id);
    }

    public List<Customer> findByName(String name) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerMap.values()) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findByEmail(String email) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerMap.values()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findByPhone(String phone) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerMap.values()) {
            if (c.getPhone().equalsIgnoreCase(phone)) {
                result.add(c);
            }
        }
        return result;
    }
}
