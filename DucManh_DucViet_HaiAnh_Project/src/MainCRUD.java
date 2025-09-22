import java.util.ArrayList;
import java.util.List;

public class MainCRUD {
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Product p1 = new Product("P01", "IPhone 15", "Phone", 28_000_000);
        Product p2 = new Product("P02", "Laptop Dell", "Laptop", 22_000_000);
        products.add(p1);
        products.add(p2);

        Customer c1 = new Customer("C01", "Nguyen Van A", "a@gmail.com", "0901234567");
        Customer c2 = new Customer("C02", "Le Thi B", "b@gmail.com", "0902345678");
        customers.add(c1);
        customers.add(c2);

        Order o1 = new Order("O01", c1);
        o1.addItem(p1, 1);
        o1.addItem(p2, 2);
        orders.add(o1);

        System.out.println("Sản phẩm: ");
        for (Product p : products) System.out.println(p);

        System.out.println("\nKhách hàng: ");
        for (Customer c : customers) System.out.println(c);

        System.out.println("\nĐơn hàng: ");
        for (Order o : orders) System.out.println(o);

        // Demo cập nhật/xóa
        o1.updateItemQty("P02", 3);     
        o1.removeItem("P01");       
        System.out.println("\nĐơn hàng sau cập nhật:");
        System.out.println(o1);
    }
}
