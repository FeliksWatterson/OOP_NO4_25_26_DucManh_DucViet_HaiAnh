import java.util.*;
import Model.Product;

public class TestProduct {
    private List<Product> products = new ArrayList<>();

    public void add(Product p) {
        products.add(p);
    }

    public List<Product> getAll() {
        return products;
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void remove(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
