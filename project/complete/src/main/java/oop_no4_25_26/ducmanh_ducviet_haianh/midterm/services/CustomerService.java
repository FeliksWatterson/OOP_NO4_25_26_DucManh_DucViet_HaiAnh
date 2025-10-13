package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.services;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    // Tạo khách hàng mới
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Hiển thị toàn bộ khách hàng
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Tìm khách hàng theo ID
    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // Cập nhật thông tin khách hàng
    public boolean updateCustomer(String id, String fullName, String email, String phone) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customer.setFullName(fullName);
            customer.setEmail(email);
            customer.setPhone(phone);
            return true;
        }
        return false;
    }

    // Xóa khách hàng
    public boolean deleteCustomer(String id) {
        return customers.removeIf(c -> c.getId().equalsIgnoreCase(id));
    }
}
