import java.nio.file.Files;
import java.nio.file.Path;

public class TestShopManager {
    public static void main(String[] args) throws Exception {
        Path tempDir = Files.createTempDirectory("shop-manager-test");
        ShopManager manager = new ShopManager(tempDir);
        manager.clearAll();

        Product phone = manager.addProduct("TP01", "Test Phone", "Phone", 10_000_000);
        Product laptop = manager.addProduct("TL01", "Test Laptop", "Laptop", 25_000_000);

        Customer customer = manager.addCustomer("TC01", "Test Customer", "test@example.com", "0900000000");

        Order order = manager.createOrder("TO01", customer.getId());
        manager.setOrderItem(order.getId(), phone.getId(), 2);
        manager.addItemToOrder(order.getId(), laptop.getId(), 1);

        System.out.println("Số sản phẩm: " + manager.listProducts().size());
        System.out.println("Sản phẩm giá 15-30 triệu: " + manager.filterProductsByPriceRange(15_000_000, 30_000_000));
        System.out.println("Khách hàng tìm kiếm 'test': " + manager.searchCustomers("test"));
        System.out.println("Đơn hàng tạo ra: " + manager.listOrders().size());
        System.out.println("Tổng tiền đơn hàng: " + manager.findOrderById(order.getId()).get().getTotal());
    }
}
