// CRUD DUCMANH
package oop_no4_25_26.ducmanh_ducviet_haianh.code.services;

import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<String, Product> products = new HashMap<>();

    public ProductService() {
        products.put("P01", new Product("P01", "IPhone 15", "Phone", 28000000));
        products.put("P02", new Product("P02", "Laptop Dell", "Laptop", 22000000));
        products.put("P03", new Product("P03", "AirPods 3", "Accessory", 4000000));
    }
    //Tạo
    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Sản phẩm với ID " + product.getId() + " đã tồn tại!");
        }
        products.put(product.getId(), product);
    }

    // Đọc
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    // Sửa
    public void updateProduct(String id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm có ID " + id);
        }
        products.put(id, updatedProduct);
    }

    // Xóa
    public void deleteProduct(String id) {
        products.remove(id);
    }
}
