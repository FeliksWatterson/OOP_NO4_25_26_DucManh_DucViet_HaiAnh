package oop_no4_25_26.ducmanh_ducviet_haianh.code.services;

import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public String runCoreProgram() {
        Customer c1 = new Customer("C01","Nguyễn Văn A","a@example.com","0901234567");
        Product iphone = new Product("P01","iPhone 15","Phone", 28000000);
        Product laptop = new Product("P02","Dell Inspiron","Laptop", 22000000);
        Product keyboard = new Product("P03","Logitech MX Keys","Accessory", 2500000);

        Order order = new Order("O01", c1);
        order.addItem(iphone,1);
        order.addItem(laptop,1);
        order.addItem(keyboard,2);

        return order.summary();
    }
}
