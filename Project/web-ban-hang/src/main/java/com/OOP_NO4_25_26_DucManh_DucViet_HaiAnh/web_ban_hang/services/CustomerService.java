package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.CustomerInterface;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Order;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.OrderItem;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.AddressRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.CartItemRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.CustomerRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.OrderItemRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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
        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(rawPassword)) {
            return customerOpt;
        }
        return Optional.empty();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer != null && customer.getCustomerId() != null) {
            customerRepository.save(customer);
        }
    }

    @Override
    @Transactional
    public boolean updatePassword(Long customerId, String oldPassword, String newPassword) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.getPassword().equals(oldPassword)) {
                customer.setPassword(newPassword);
                customerRepository.save(customer);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            List<Order> customerOrders = orderRepository.findByCustomerOrderByOrderDateDesc(customer);
            for (Order order : customerOrders) {
                List<OrderItem> orderItems = order.getOrderItems(); 
                if (orderItems != null && !orderItems.isEmpty()) {
                    orderItemRepository.deleteAll(orderItems);
                }
                orderRepository.delete(order);
            }
            addressRepository.findByCustomer(customer).forEach(addressRepository::delete);
            cartItemRepository.deleteByCustomer(customer);

            customerRepository.deleteById(customerId);
        } else {
            throw new RuntimeException("Không thể xóa tài khoản. Đã xảy ra lỗi không mong muốn.");
        }
    }
}