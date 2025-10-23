package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Thêm import này

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable("id") Long productId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> productOpt = productService.findProductById(productId);

        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            // Bạn có thể thêm các thuộc tính khác vào model nếu cần, ví dụ: sản phẩm liên quan
            // model.addAttribute("relatedProducts", productService.findRelatedProducts(productOpt.get()));
            return "productdetail"; // Trả về tên file HTML (không có .html)
        } else {
            // Xử lý trường hợp không tìm thấy sản phẩm
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm với ID: " + productId);
            return "redirect:/"; // Chuyển hướng về trang chủ hoặc trang lỗi
        }
    }
}