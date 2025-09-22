import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }

    public void addItem(Product p, int quantity) {
        items.add(new OrderItem(p, quantity));
    }

    public boolean updateItemQty(String productId, int newQty) {
        for (OrderItem it : items) {
            if (it.getProduct().getId().equals(productId)) {
                it.setQuantity(newQty);
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(String productId) {
        return items.removeIf(it -> it.getProduct().getId().equals(productId));
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
          .append("Items: \n");
        for (OrderItem item : items) sb.append("  ").append(item).append("\n");
        sb.append("Total: ").append(getTotal());
        return sb.toString();
    }
}
