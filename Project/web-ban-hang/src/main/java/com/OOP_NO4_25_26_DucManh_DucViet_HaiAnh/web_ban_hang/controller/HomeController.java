package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.ProductInterface; // Import ProductInterface
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product; // Import Product model
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import Model
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List; // Import List

@Controller
public class HomeController {

    @Autowired // Tiêm ProductInterface
    private ProductInterface productService;

    @GetMapping("/")
    public String showHomePage(Model model) { // Thêm Model vào tham số
        List<Product> products = productService.findAllProducts(); // Lấy danh sách sản phẩm
        model.addAttribute("products", products); // Thêm danh sách vào model để Thymeleaf sử dụng
        // Bạn có thể thêm các attribute khác vào model nếu cần cho trang index
        // Ví dụ: model.addAttribute("welcomeMessage", session.getAttribute("welcomeMessage"));
        return "index";
    }
}