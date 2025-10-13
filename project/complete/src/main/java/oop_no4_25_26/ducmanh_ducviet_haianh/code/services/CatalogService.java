package oop_no4_25_26.ducmanh_ducviet_haianh.code.services;

import jakarta.annotation.PostConstruct;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Customer;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatalogService {
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Customer> customers = new LinkedHashMap<>();

    @PostConstruct
    public void seed() {
        products.put("P01", new Product("P01","iPhone 15","Phone", 28000000));
        products.put("P02", new Product("P02","Dell Inspiron","Laptop", 22000000));
        products.put("P03", new Product("P03","Logitech MX Keys","Accessory", 2500000));
        products.put("P04", new Product("P04","Kindle Paperwhite","E-reader", 3200000));

        customers.put("C01", new Customer("C01","Nguyễn Văn A","a@example.com","0901234567"));
        customers.put("C02", new Customer("C02","Trần Thị B","b@example.com","0902345678"));
        customers.put("C03", new Customer("C03","Lê Văn C","c@example.com","0903456789"));
    }

    public List<Product> allProducts() { return new ArrayList<>(products.values()); }
    public List<Customer> allCustomers() { return new ArrayList<>(customers.values()); }
    public Product product(String id) { return products.get(id); }
    public Customer customer(String id) { return customers.get(id); }
}
