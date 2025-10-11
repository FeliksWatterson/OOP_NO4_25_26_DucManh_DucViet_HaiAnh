// DUCMANH
package oop_no4_25_26.ducmanh_ducviet_haianh.midterm;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Product;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.services.ProductService;

import java.util.List;
import java.util.Scanner;

public class CrudProduct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService service = new ProductService();
        int choice;

        do {
            System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    List<Product> list = service.getAllProducts();
                    if (list.isEmpty()) {
                        System.out.println("Chưa có sản phẩm nào.");
                    } else {
                        System.out.println("Danh sách sản phẩm:");
                        list.forEach(System.out::println);
                    }
                }
                case 2 -> {
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tên sản phẩm: ");
                    String name = sc.nextLine();
                    System.out.print("Danh mục: ");
                    String cat = sc.nextLine();
                    System.out.print("Đơn giá: ");
                    long price = Long.parseLong(sc.nextLine());
                    try {
                        service.addProduct(new Product(id, name, cat, price));
                        System.out.println("Đã thêm sản phẩm thành công!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(" " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Nhập ID cần sửa: ");
                    String id = sc.nextLine();
                    Product p = service.getProductById(id);
                    if (p == null) {
                        System.out.println(" Không tìm thấy sản phẩm!");
                    } else {
                        System.out.print("Tên mới (" + p.getName() + "): ");
                        String name = sc.nextLine();
                        System.out.print("Danh mục mới (" + p.getCategory() + "): ");
                        String cat = sc.nextLine();
                        System.out.print("Giá mới (" + p.getUnitPrice() + "): ");
                        long price = Long.parseLong(sc.nextLine());
                        service.updateProduct(id, new Product(id, name, cat, price));
                        System.out.println(" Đã cập nhật sản phẩm!");
                    }
                }
                case 4 -> {
                    System.out.print("Nhập ID cần xóa: ");
                    String id = sc.nextLine();
                    if (service.getProductById(id) == null) {
                        System.out.println(" Không tìm thấy sản phẩm!");
                    } else {
                        service.deleteProduct(id);
                        System.out.println("Đã xóa sản phẩm!");
                    }
                }
                case 0 -> System.out.println("Đã thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
