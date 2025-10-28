package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Order;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.OrderService;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.CustomerInterface;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Address;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.AddressInterface;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired 
    private AddressInterface addressService;

    @Autowired
    private CustomerInterface customerService;

    @Autowired 
    private OrderService orderService;

    @GetMapping("/auth")
    public String showAuthPage(Model model, HttpSession session) {
        if (session.getAttribute("loggedInCustomer") != null) {
            return "redirect:/account";
        }

        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new Customer());
        }
        return "auth";
    }

    // Sign Up
     @PostMapping("/register")
    public String processRegistration(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.registerCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            redirectAttributes.addFlashAttribute("popup", true);
            return "redirect:/auth";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            customer.setPassword(null); 
            redirectAttributes.addFlashAttribute("customer", customer);
            redirectAttributes.addFlashAttribute("showRegister", true);
            return "redirect:/auth";
        }
    }

    // Sign In
    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model,
            RedirectAttributes redirectAttributes, HttpSession session) { 
        Optional<Customer> authenticatedCustomer = customerService.authenticateCustomer(email, password);

        if (authenticatedCustomer.isPresent()) {
            Customer customer = authenticatedCustomer.get();
            session.setAttribute("loggedInCustomer", customer);

            redirectAttributes.addFlashAttribute("welcomeMessage", "Chào mừng trở lại, " + customer.getFullName() + "!");
            redirectAttributes.addFlashAttribute("popup", true);
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Email hoặc mật khẩu không chính xác.");
            model.addAttribute("email", email);
            if (!model.containsAttribute("customer")) {
                model.addAttribute("customer", new Customer());
            }
            return "auth";
        }
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        if (customer == null) {
            return "redirect:/auth";
        }
        Optional<Customer> freshCustomerOpt = customerService.findCustomerByEmail(customer.getEmail());
        if (freshCustomerOpt.isEmpty()) {
            session.invalidate(); 
            return "redirect:/auth";
        }
        Customer freshCustomer = freshCustomerOpt.get();
        model.addAttribute("customer", freshCustomer);

        Optional<Address> defaultAddressOpt = addressService.findDefaultAddressByCustomer(freshCustomer);
        model.addAttribute("defaultAddress", defaultAddressOpt.orElse(null)); 

        long addressCount = addressService.countAddressesByCustomer(freshCustomer);
        model.addAttribute("addressCount", addressCount);

        List<Order> orders = orderService.findOrdersByCustomer(freshCustomer);
        model.addAttribute("orders", orders);


        return "account";
    }

    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }
}