package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.repository;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Product;
import java.util.*;

public class ProductRepository {
    private final Map<Long, Product> productMap = new HashMap<>();
    private long currentId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(currentId++);
        }
        productMap.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }

    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : productMap.values()) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> findByType(String type) {
        List<Product> result = new ArrayList<>();
        for (Product p : productMap.values()) {
            if (p.getType().equalsIgnoreCase(type)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> findByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : productMap.values()) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }
}
