package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Customer customer;
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private double totalAmount;

    public Order() {}

    public Order(Long id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<OrderDetail> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetail> orderDetails) { this.orderDetails = orderDetails; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
