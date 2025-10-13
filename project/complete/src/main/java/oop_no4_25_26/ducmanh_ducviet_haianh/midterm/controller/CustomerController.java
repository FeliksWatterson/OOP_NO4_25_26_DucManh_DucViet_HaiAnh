package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.controller;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Customer;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.services.CustomerService;

public class CustomerController {
    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        // Tạo khách hàng mới
        service.addCustomer(new Customer("C001", "Lê Nguyễn Hải Anh", "haianh@gmail.com", "0901234567"));
        service.addCustomer(new Customer("C002", "Nguyễn Đức Mạnh", "manh@gmail.com", "0912345678"));

        // Hiển thị danh sách
        System.out.println("Danh sách khách hàng:");
        service.getAllCustomers().forEach(System.out::println);

        // Cập nhật thông tin
        service.updateCustomer("C001", "Hải Anh (Updated)", "newmail@gmail.com", "0909999999");
        System.out.println("\nSau khi cập nhật:");
        service.getAllCustomers().forEach(System.out::println);

        // Xóa khách hàng
        service.deleteCustomer("C002");
        System.out.println("\nSau khi xóa:");
        service.getAllCustomers().forEach(System.out::println);
    }
}
