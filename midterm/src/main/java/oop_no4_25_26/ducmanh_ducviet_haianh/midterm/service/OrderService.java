package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.service;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Order;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.OrderDetail;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Product;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.repository.OrderRepository;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        double total = 0;
        for (OrderDetail detail : order.getOrderDetails()) {
            detail.setPrice(detail.getProduct().getPrice() * detail.getQuantity());
            total += detail.getPrice();
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
