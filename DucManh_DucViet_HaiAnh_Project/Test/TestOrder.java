import java.util.*;
import Model.Order;

public class TestOrder {
    private List<Order> orders = new ArrayList<>();

    public void add(Order o) {
        orders.add(o);
    }

    public List<Order> getAll() {
        return orders;
    }

    public Order findById(String id) {
        for (Order o : orders) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    public void remove(String id) {
        orders.removeIf(o -> o.getId().equals(id));
    }
}
