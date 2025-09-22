import java.util.Optional;

public class App {
    public static void main(String[] args) {
        ShopManager manager = new ShopManager();
        seedDemoData(manager);

        System.out.println("=== Danh sách sản phẩm ===");
        manager.listProducts().forEach(System.out::println);

        System.out.println("\n=== Sản phẩm chứa từ khóa 'Laptop' ===");
        manager.filterProductsByName("Laptop").forEach(System.out::println);

        System.out.println("\n=== Sản phẩm thuộc loại 'Accessories' ===");
        manager.filterProductsByType("Accessories").forEach(System.out::println);

        System.out.println("\n=== Sản phẩm giá từ 20 triệu - 30 triệu ===");
        manager.filterProductsByPriceRange(20_000_000, 30_000_000).forEach(System.out::println);

        System.out.println("\n=== Tìm kiếm khách hàng với từ khóa 'Nguyen' ===");
        manager.searchCustomers("Nguyen").forEach(System.out::println);

        System.out.println("\n=== Đơn hàng hiện có ===");
        for (Order order : manager.listOrders()) {
            System.out.println(order);
            System.out.println("---------------------------");
        }

        manager.updateOrderItem("O01", "P02", 1);
        System.out.println("\nSau khi cập nhật số lượng Laptop trong đơn hàng O01 còn 1:");
        manager.findOrderById("O01").ifPresent(System.out::println);

        Optional<Customer> customer = manager.findCustomerById("C01");
        customer.ifPresent(c -> {
            System.out.println("\nĐơn hàng của khách hàng " + c.getName() + ":");
            manager.listOrdersByCustomer(c.getId())
                   .forEach(o -> System.out.println(o.getId() + " - Tổng tiền: " + o.getTotal()));
        });
    }

    private static void seedDemoData(ShopManager manager) {
        Product iphone = manager.findProductById("P01")
                .orElseGet(() -> manager.addProduct("P01", "IPhone 15", "Phone", 28_000_000));
        Product laptop = manager.findProductById("P02")
                .orElseGet(() -> manager.addProduct("P02", "Laptop Dell", "Laptop", 22_000_000));
        Product airpods = manager.findProductById("P03")
                .orElseGet(() -> manager.addProduct("P03", "AirPods Pro", "Accessories", 5_500_000));

        Customer nguyen = manager.findCustomerById("C01")
                .orElseGet(() -> manager.addCustomer("C01", "Nguyen Van A", "a@gmail.com", "0901234567"));
        Customer tran = manager.findCustomerById("C02")
                .orElseGet(() -> manager.addCustomer("C02", "Tran Thi B", "b@gmail.com", "0902345678"));

        Order order1 = manager.findOrderById("O01")
                .orElseGet(() -> manager.createOrder("O01", nguyen.getId()));
        manager.setOrderItem(order1.getId(), iphone.getId(), 1);
        manager.setOrderItem(order1.getId(), laptop.getId(), 2);
        manager.setOrderItem(order1.getId(), airpods.getId(), 3);

        Order order2 = manager.findOrderById("O02")
                .orElseGet(() -> manager.createOrder("O02", tran.getId()));
        manager.setOrderItem(order2.getId(), airpods.getId(), 1);

        manager.saveAll();
    }
}
