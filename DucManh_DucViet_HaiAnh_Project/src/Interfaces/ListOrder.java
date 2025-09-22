package Interfaces;

import java.util.List;
import Model.Order;

public interface ListOrder {
    void add(Order o);
    List<Order> getAll();
    Order findById(String id);
    void remove(String id);
}
