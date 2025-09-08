import java.util.ArrayList;
import java.util.List;

public class Recursion {
    private String name;
    private List<Recursion> subCategories = new ArrayList<>();
    private List<String> products = new ArrayList<>();

    public Recursion(String name) {
        this.name = name;
    }

    public void addProduct(String product) {
        products.add(product);
    }

    public void addSubRecursion(Recursion sub) {
        subCategories.add(sub);
    }

    public String getName() {
        return name;
    }

    public List<Recursion> getSubCategories() {
        return subCategories;
    }

    public List<String> getProducts() {
        return products;
    }

    public int countProducts() {
        int total = products.size();
        for (Recursion sub : subCategories) {
            total += sub.countProducts(); 
        }
        return total;
    }

    public boolean containsProduct(String productName) {
        if (products.contains(productName)) {
            return true;
        }
        for (Recursion sub : subCategories) {
            if (sub.containsProduct(productName)) {
                return true;
            }
        }
        return false;
    }
}
