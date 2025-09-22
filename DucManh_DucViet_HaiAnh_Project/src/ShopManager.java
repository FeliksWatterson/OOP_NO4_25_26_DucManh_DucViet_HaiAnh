import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ShopManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Order> orders;

    private final DataStore<Product> productStore;
    private final DataStore<Customer> customerStore;
    private final DataStore<Order> orderStore;

    public ShopManager() {
        this(Paths.get("data"));
    }

    public ShopManager(Path dataDirectory) {
        Path dir = dataDirectory == null ? Paths.get("data") : dataDirectory;
        this.productStore = new DataStore<>(dir.resolve("products.dat"), Product.class);
        this.customerStore = new DataStore<>(dir.resolve("customers.dat"), Customer.class);
        this.orderStore = new DataStore<>(dir.resolve("orders.dat"), Order.class);
        this.products = new ArrayList<>(productStore.load());
        this.customers = new ArrayList<>(customerStore.load());
        this.orders = new ArrayList<>(orderStore.load());
    }

    public List<Product> listProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    public List<Customer> listCustomers() {
        return Collections.unmodifiableList(new ArrayList<>(customers));
    }

    public List<Order> listOrders() {
        return Collections.unmodifiableList(new ArrayList<>(orders));
    }

    public Optional<Product> findProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public Optional<Customer> findCustomerById(String id) {
        return customers.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public Optional<Order> findOrderById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public Product addProduct(String id, String name, String type, double price) {
        Product product = new Product(id, name, type, price);
        addProduct(product);
        return product;
    }

    public void addProduct(Product product) {
        if (findProductById(product.getId()).isPresent()) {
            throw new IllegalArgumentException("Product id already exists: " + product.getId());
        }
        products.add(product);
        saveProducts();
    }

    public void updateProduct(String id, String name, String type, double price) {
        Product product = requireProduct(id);
        if (name != null && !name.isBlank()) {
            product.setName(name);
        }
        if (type != null && !type.isBlank()) {
            product.setType(type);
        }
        product.setPrice(price);
        saveProducts();
    }

    public boolean removeProduct(String id) {
        boolean removed = products.removeIf(p -> p.getId().equalsIgnoreCase(id));
        if (removed) {
            for (Order order : orders) {
                order.removeItem(id);
            }
            saveProducts();
            saveOrders();
        }
        return removed;
    }

    public Customer addCustomer(String id, String name, String email, String phone) {
        Customer customer = new Customer(id, name, email, phone);
        addCustomer(customer);
        return customer;
    }

    public void addCustomer(Customer customer) {
        if (findCustomerById(customer.getId()).isPresent()) {
            throw new IllegalArgumentException("Customer id already exists: " + customer.getId());
        }
        customers.add(customer);
        saveCustomers();
    }

    public void updateCustomer(String id, String name, String email, String phone) {
        Customer customer = requireCustomer(id);
        if (name != null && !name.isBlank()) {
            customer.setName(name);
        }
        if (email != null && !email.isBlank()) {
            customer.setEmail(email);
        }
        if (phone != null && !phone.isBlank()) {
            customer.setPhone(phone);
        }
        saveCustomers();
    }

    public boolean removeCustomer(String id) {
        boolean removed = customers.removeIf(c -> c.getId().equalsIgnoreCase(id));
        if (removed) {
            orders.removeIf(order -> order.getCustomer().getId().equalsIgnoreCase(id));
            saveCustomers();
            saveOrders();
        }
        return removed;
    }

    public List<Product> filterProductsByName(String keyword) {
        return filterProducts(p -> p.matchesName(keyword));
    }

    public List<Product> filterProductsByType(String type) {
        return filterProducts(p -> p.matchesType(type));
    }

    public List<Product> filterProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            double temp = minPrice;
            minPrice = maxPrice;
            maxPrice = temp;
        }
        double min = minPrice;
        double max = maxPrice;
        return filterProducts(p -> p.withinPriceRange(min, max));
    }

    private List<Product> filterProducts(Predicate<Product> predicate) {
        return products.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Customer> searchCustomers(String keyword) {
        return customers.stream()
                .filter(c -> c.matchesKeyword(keyword))
                .collect(Collectors.toList());
    }

    public List<Order> listOrdersByCustomer(String customerId) {
        return orders.stream()
                .filter(o -> o.getCustomer().getId().equalsIgnoreCase(customerId))
                .collect(Collectors.toList());
    }

    public Order createOrder(String orderId, String customerId) {
        if (findOrderById(orderId).isPresent()) {
            throw new IllegalArgumentException("Order id already exists: " + orderId);
        }
        Customer customer = requireCustomer(customerId);
        Order order = new Order(orderId, customer);
        orders.add(order);
        saveOrders();
        return order;
    }

    public void addItemToOrder(String orderId, String productId, int quantity) {
        Order order = requireOrder(orderId);
        Product product = requireProduct(productId);
        order.addItem(product, quantity);
        saveOrders();
    }

    public void setOrderItem(String orderId, String productId, int quantity) {
        Order order = requireOrder(orderId);
        Product product = requireProduct(productId);
        order.setItemQuantity(product, quantity);
        saveOrders();
    }

    public boolean updateOrderItem(String orderId, String productId, int quantity) {
        Order order = requireOrder(orderId);
        boolean updated = order.updateItemQty(productId, quantity);
        if (updated) {
            saveOrders();
        }
        return updated;
    }

    public boolean removeOrderItem(String orderId, String productId) {
        Order order = requireOrder(orderId);
        boolean removed = order.removeItem(productId);
        if (removed) {
            saveOrders();
        }
        return removed;
    }

    public boolean removeOrder(String orderId) {
        boolean removed = orders.removeIf(o -> o.getId().equalsIgnoreCase(orderId));
        if (removed) {
            saveOrders();
        }
        return removed;
    }

    public void saveAll() {
        saveProducts();
        saveCustomers();
        saveOrders();
    }

    public void clearAll() {
        products.clear();
        customers.clear();
        orders.clear();
        saveAll();
    }

    private Product requireProduct(String productId) {
        return findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + productId));
    }

    private Customer requireCustomer(String customerId) {
        return findCustomerById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found: " + customerId));
    }

    private Order requireOrder(String orderId) {
        return findOrderById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found: " + orderId));
    }

    private void saveProducts() {
        productStore.save(products);
    }

    private void saveCustomers() {
        customerStore.save(customers);
    }

    private void saveOrders() {
        orderStore.save(orders);
    }
}
