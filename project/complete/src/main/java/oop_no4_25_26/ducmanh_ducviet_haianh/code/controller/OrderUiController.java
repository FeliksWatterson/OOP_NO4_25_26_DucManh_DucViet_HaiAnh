package oop_no4_25_26.ducmanh_ducviet_haianh.code.controller;

import jakarta.servlet.http.HttpSession;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Order;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Product;
import oop_no4_25_26.ducmanh_ducviet_haianh.code.services.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderUiController {

    private static final String SESSION_ORDER = "CURRENT_ORDER";
    private final CatalogService catalog;

    public OrderUiController(CatalogService catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/shop")
    public String shop(Model model, HttpSession session) {
        model.addAttribute("customers", catalog.allCustomers());
        model.addAttribute("products", catalog.allProducts());
        model.addAttribute("order", session.getAttribute(SESSION_ORDER));
        return "shop";
    }

    @PostMapping("/shop/create-order")
    public String createOrder(@RequestParam String orderId,
                              @RequestParam String customerId,
                              HttpSession session) {
        var customer = catalog.customer(customerId);
        session.setAttribute(SESSION_ORDER, new Order(orderId, customer));
        return "redirect:/shop";
    }

    @PostMapping("/shop/add-item")
    public String addItem(@RequestParam String productId,
                          @RequestParam int quantity,
                          HttpSession session) {
        var order = (Order) session.getAttribute(SESSION_ORDER);
        if (order != null && quantity > 0) {
            Product p = catalog.product(productId);
            order.addItem(p, quantity);
        }
        return "redirect:/";
    }

    @PostMapping("/shop/remove-item")
    public String removeItem(@RequestParam String productId, HttpSession session) {
        var order = (Order) session.getAttribute(SESSION_ORDER);
        if (order != null) {
            order.getItems().removeIf(it -> it.getProduct().getId().equals(productId));
        }
        return "redirect:/shop";
    }

    @PostMapping("/shop/reset")
    public String reset(HttpSession session) {
        session.removeAttribute(SESSION_ORDER);
        return "redirect:/shop";
    }
}
