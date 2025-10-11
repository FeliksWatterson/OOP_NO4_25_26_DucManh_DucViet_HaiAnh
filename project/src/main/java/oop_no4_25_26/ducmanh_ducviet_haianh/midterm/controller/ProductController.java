// CRUD DUCMANH
package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.controller;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Product;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    // Hiển thị thêm sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product("", "", "", 0));
        return "products/form";
    }

    // Lưu sản phẩm
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    // Sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products/form";
    }

    // Cập nhật sản phẩm
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
