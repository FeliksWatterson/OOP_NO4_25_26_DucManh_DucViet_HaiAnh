import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private final LocalDateTime createdAt;

    public Order(String id, Customer customer) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Order id cannot be blank");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
        this.id = id.trim();
        this.customer = customer;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
    }

    public List<OrderItem> getItems() { return items; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void addItem(Product p, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        Optional<OrderItem> existing = findItem(p.getId());
        if (existing.isPresent()) {
            OrderItem item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.add(new OrderItem(p, quantity));
        }
    }

    public void setItemQuantity(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            removeItem(product.getId());
            return;
        }
        Optional<OrderItem> existing = findItem(product.getId());
        if (existing.isPresent()) {
            existing.get().setQuantity(quantity);
        } else {
            items.add(new OrderItem(product, quantity));
        }
    }

    public Optional<OrderItem> findItem(String productId) {
        if (productId == null) {
            return Optional.empty();
        }
        return items.stream()
                .filter(it -> it.getProduct().getId().equalsIgnoreCase(productId.trim()))
                .findFirst();
    }

    public boolean updateItemQty(String productId, int newQty) {
        for (int i = 0; i < items.size(); i++) {
            OrderItem it = items.get(i);
            if (it.getProduct().getId().equalsIgnoreCase(productId)) {
                if (newQty <= 0) {
                    items.remove(i);
                } else {
                    it.setQuantity(newQty);
                }
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(String productId) {
        if (productId == null) {
            return false;
        }
        return items.removeIf(it -> it.getProduct().getId().equalsIgnoreCase(productId.trim()));
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) total += item.getSubtotal();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n")
          .append("Customer: ").append(customer.getName()).append("\n")
          .append("Created at: ")
          .append(createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault())))
          .append("\n")
          .append("Items: \n");
        for (OrderItem item : items) sb.append("  ").append(item).append("\n");
        sb.append("Total: ").append(getTotal());
        return sb.toString();
    }
}
