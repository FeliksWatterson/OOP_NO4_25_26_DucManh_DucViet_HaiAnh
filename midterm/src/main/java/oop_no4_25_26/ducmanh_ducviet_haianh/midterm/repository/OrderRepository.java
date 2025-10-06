package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.repository;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Order;
import java.util.*;

public class OrderRepository {
    private final Map<Long, Order> orderMap = new HashMap<>();
    private long currentId = 1;

    public List<Order> findAll() {
        return new ArrayList<>(orderMap.values());
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(currentId++);
        }
        orderMap.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderMap.get(id));
    }

    public void deleteById(Long id) {
        orderMap.remove(id);
    }
}
