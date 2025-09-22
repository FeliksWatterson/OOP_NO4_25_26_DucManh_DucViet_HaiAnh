import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String type;
    private double price;

    public Product(String id, String name, String type, double price) {
        setId(id);
        setName(name);
        setType(type);
        setPrice(price);
    }

    public String getId() { return id; }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be blank");
        }
        this.id = id.trim();
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        this.name = name.trim();
    }

    public String getType() { return type; }

    public void setType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Product type cannot be blank");
        }
        this.type = type.trim();
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = price;
    }

    public boolean matchesName(String keyword) {
        return keyword != null && name.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT));
    }

    public boolean matchesType(String requestedType) {
        return requestedType != null && type.equalsIgnoreCase(requestedType.trim());
    }

    public boolean withinPriceRange(double minPrice, double maxPrice) {
        return price >= minPrice && price <= maxPrice;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + type + " - " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equalsIgnoreCase(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.toLowerCase(Locale.ROOT));
    }
}
