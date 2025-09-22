package Interfaces;

import java.util.List;
import Model.Product;

public interface ListProduct {
    void add(Product p);
    List<Product> getAll();
    Product findById(String id);
    void remove(String id);
}
