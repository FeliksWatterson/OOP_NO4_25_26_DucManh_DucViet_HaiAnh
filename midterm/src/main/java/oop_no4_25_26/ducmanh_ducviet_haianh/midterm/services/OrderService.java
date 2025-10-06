package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.services;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String runCoreProgram() {
        Customer c1 = new Customer("C01", "Nguyễn Văn A", "a@example.com", "0901234567");

        Product iphone  = new Product("P01", "iPhone 15", "Phone", 28_000_000);
        Product laptop  = new Product("P02", "Dell Inspiron", "Laptop", 22_000_000);
        Product keyboard= new Product("P03", "Logitech MX Keys", "Accessory", 2_500_000);

        Order order = new Order("O01", c1);
        order.addItem(iphone,   1);
        order.addItem(keyboard, 2);

        order.addItem(keyboard, 1);    
        order.addItem(laptop,   1);

        StringBuilder sb = new StringBuilder();
        sb.append(order.summary());

        sb.append("\n== Cập nhật giá/SL rồi tính lại ==\n");
        keyboard.setUnitPrice(2_300_000); 
        order.addItem(iphone, 1);         
        sb.append(order.summary());

        sb.append("\nDemo hoàn tất – tất cả các đối tượng (Product, Customer, Order, OrderItem) đã tham gia đầy đủ.");
        return sb.toString();
    }
}
