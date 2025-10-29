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

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<Customer> authenticatedCustomer = customerService.authenticateCustomer(email, password);

        if (authenticatedCustomer.isPresent()) {
            Customer customer = authenticatedCustomer.get();
            session.setAttribute("loggedInCustomer", customer);

            redirectAttributes.addFlashAttribute("welcomeMessage", "Chào mừng trở lại, " + customer.getFullName() + "!");
            redirectAttributes.addFlashAttribute("popup", true);

            String redirectAfterLogin = (String) session.getAttribute("redirectAfterLogin");
            if (redirectAfterLogin != null && !redirectAfterLogin.isEmpty()) {
                 session.removeAttribute("redirectAfterLogin"); 
                 if ("/cart/handle-buy-now".equals(redirectAfterLogin)) {
                      Long buyNowProductId = (Long) session.getAttribute("buyNowProduct");
                      Integer buyNowQuantity = (Integer) session.getAttribute("buyNowQuantity");
                      session.removeAttribute("buyNowProduct");
                      session.removeAttribute("buyNowQuantity");
                      if (buyNowProductId != null && buyNowQuantity != null) {
                           try {
                               return "redirect:/checkout"; 
                           } catch (Exception e) {
                                redirectAttributes.addFlashAttribute("cartErrorMessage", "Lỗi khi thêm sản phẩm mua ngay: " + e.getMessage());
                                return "redirect:/";
                           }
                      }
                 }
                 return "redirect:" + redirectAfterLogin;
            }

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
        session.setAttribute("loggedInCustomer", freshCustomer);
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
     public String processLogout(HttpSession session, RedirectAttributes redirectAttributes) {
         session.invalidate();
         redirectAttributes.addFlashAttribute("logoutMessage", "Bạn đã đăng xuất thành công.");
         return "redirect:/";
     }

    @GetMapping("/account/change-password")
    public String showChangePasswordForm(HttpSession session, Model model) {
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
        session.setAttribute("loggedInCustomer", freshCustomer);
        model.addAttribute("customer", freshCustomer);

        Optional<Address> defaultAddressOpt = addressService.findDefaultAddressByCustomer(freshCustomer);
        model.addAttribute("defaultAddress", defaultAddressOpt.orElse(null));
        long addressCount = addressService.countAddressesByCustomer(freshCustomer);
        model.addAttribute("addressCount", addressCount);
        List<Order> orders = orderService.findOrdersByCustomer(freshCustomer);
        model.addAttribute("orders", orders);
        model.addAttribute("showChangePasswordFormOnLoad", true);

        return "account"; 
    }

    @PostMapping("/account/change-password")
    public String processChangePassword(@RequestParam String oldPassword,
                                        @RequestParam String newPassword,
                                        @RequestParam String confirmPassword,
                                        HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("passwordError", "Mật khẩu mới không khớp.");
            redirectAttributes.addFlashAttribute("showChangePasswordForm", true);
            return "redirect:/account"; 
        }

        boolean success = customerService.updatePassword(customer.getCustomerId(), oldPassword, newPassword);

        if (success) {
            redirectAttributes.addFlashAttribute("passwordSuccess", "Đổi mật khẩu thành công!");
        } else {
            redirectAttributes.addFlashAttribute("passwordError", "Mật khẩu cũ không chính xác.");
            redirectAttributes.addFlashAttribute("showChangePasswordForm", true);
        }
        return "redirect:/account";
    }

    @PostMapping("/account/delete")
    public String processDeleteAccount(HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }

        try {
            customerService.deleteCustomer(customer.getCustomerId());
            session.invalidate(); 
            redirectAttributes.addFlashAttribute("deleteSuccess", "Tài khoản của bạn đã được xóa thành công.");
            return "redirect:/"; 
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("deleteError", "Lỗi khi xóa tài khoản: " + e.getMessage());
            return "redirect:/account"; 
        }
    }
}