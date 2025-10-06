package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.controller;

import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Order;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Customer;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.Product;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model.OrderDetail;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.service.OrderService;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.service.CustomerService;
import oop_no4_25_26.ducmanh_ducviet_haianh.midterm.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("products", productService.getAllProducts());
        return "order/add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
