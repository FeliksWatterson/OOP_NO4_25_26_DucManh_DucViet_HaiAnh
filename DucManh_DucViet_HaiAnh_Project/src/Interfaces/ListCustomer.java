package Interfaces;

import java.util.List;
import Model.Customer;

public interface ListCustomer {
    void add(Customer c);
    List<Customer> getAll();
    Customer findById(String id);
    void remove(String id);
}
