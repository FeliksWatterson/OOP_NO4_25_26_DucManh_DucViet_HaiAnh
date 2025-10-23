package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.ProductInterface;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductInterface productService;

    // Mapping để hiển thị trang chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> productOpt = productService.findProductById(id);

        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            // Trả về tên file HTML (không có đuôi .html) trong thư mục templates
            return "product-detail";
        } else {
            // Nếu không tìm thấy sản phẩm, thông báo lỗi và chuyển hướng về trang chủ
            redirectAttributes.addFlashAttribute("errorMessage", "Sản phẩm không tồn tại.");
            return "redirect:/";
        }
    }

     // Bạn cũng có thể đặt phương thức hiển thị danh sách sản phẩm ở đây nếu muốn
     /*
     @GetMapping("/products") // Hoặc để ở HomeController tùy cấu trúc
     public String showAllProducts(Model model) {
         List<Product> products = productService.findAllProducts();
         model.addAttribute("products", products);
         return "index"; // Hoặc một trang danh sách sản phẩm riêng
     }
     */
}