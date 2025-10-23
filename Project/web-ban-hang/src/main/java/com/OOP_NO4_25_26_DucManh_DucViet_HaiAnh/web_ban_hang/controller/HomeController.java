package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("sidebarBestSellers", productService.findSidebarBestSellers(4)); 
        model.addAttribute("newProducts", productService.findNewProducts(4)); 
        model.addAttribute("mostBoughtProducts", productService.findProductsBySection("mostBought", 4)); 
        model.addAttribute("topRatedProducts", productService.findHighlyRatedProducts(4)); 
        model.addAttribute("flashSaleProducts", productService.findProductsOnSale(2)); 
        model.addAttribute("suggestedProducts", productService.findSuggestedProducts());

        return "index";
    }
}