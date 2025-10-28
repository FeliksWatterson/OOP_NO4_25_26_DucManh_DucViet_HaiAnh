package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.*;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.AddressInterface;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.CartService;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*; // Đảm bảo import đủ
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/checkout")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressInterface addressService;

    @Autowired // Inject OrderService
    private OrderService orderService;

    private static final String CUSTOMER_SESSION_KEY = "loggedInCustomer";

    @GetMapping("")
    public String showCheckoutPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng đăng nhập để tiến hành thanh toán.");
            session.setAttribute("redirectAfterLogin", "/checkout");
            return "redirect:/auth";
        }
        List<CartItem> cartItems = cartService.getCartItems(loggedInCustomer);
        if (cartItems.isEmpty() && !"POST".equalsIgnoreCase( ((HttpServletRequest) model.getAttribute("jakarta.servlet.forward.request_uri")) != null ? ((HttpServletRequest) model.getAttribute("jakarta.servlet.forward.request_uri")).getMethod() : "GET" ) ) { // Chỉ kiểm tra giỏ hàng trống khi GET, ko phải khi POST lỗi redirect về
             redirectAttributes.addFlashAttribute("cartErrorMessage", "Giỏ hàng của bạn đang trống.");
             return "redirect:/cart";
         }
        int cartItemCount = cartService.getTotalItems(loggedInCustomer);
        Optional<Address> defaultAddressOpt = addressService.findDefaultAddressByCustomer(loggedInCustomer);
        Address defaultAddress = defaultAddressOpt.orElse(null);
        double subtotal = cartService.getSubtotal(loggedInCustomer);
        double shippingFee = (subtotal >= 100000 || subtotal == 0) ? 0 : 30000;

        
        double discount = (double) Optional.ofNullable(model.getAttribute("discountAmount")).orElse(0.0);
        String appliedDiscountCode = (String) model.getAttribute("appliedDiscountCode");
        String discountMessage = (String) model.getAttribute("discountMessage");
        String checkoutError = (String) model.getAttribute("checkoutError"); 

        double total = subtotal + shippingFee - discount;

        model.addAttribute("customer", loggedInCustomer);
        model.addAttribute("cartItems", cartItems.isEmpty() ? cartService.getCartItems(loggedInCustomer): cartItems);
        model.addAttribute("cartItemCount", cartItemCount);
        model.addAttribute("defaultAddress", defaultAddress);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("discount", discount);
        model.addAttribute("appliedDiscountCode", appliedDiscountCode);
        model.addAttribute("discountMessage", discountMessage);
        model.addAttribute("total", total);
        model.addAttribute("checkoutError", checkoutError); 
        model.addAttribute("oldEmail", model.getAttribute("oldEmail"));
        model.addAttribute("oldFullName", model.getAttribute("oldFullName"));
        model.addAttribute("oldPhone", model.getAttribute("oldPhone"));
        model.addAttribute("oldCity", model.getAttribute("oldCity"));
        model.addAttribute("oldDistrict", model.getAttribute("oldDistrict"));
        model.addAttribute("oldWard", model.getAttribute("oldWard"));
        model.addAttribute("oldAddressDetail", model.getAttribute("oldAddressDetail"));
        model.addAttribute("oldNote", model.getAttribute("oldNote"));
        model.addAttribute("oldShippingMethod", model.getAttribute("oldShippingMethod"));
        model.addAttribute("oldPaymentMethod", model.getAttribute("oldPaymentMethod"));


        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(HttpSession session,
                             @RequestParam String fullName, @RequestParam String phone,
                             @RequestParam String email, @RequestParam String city,
                             @RequestParam String district, @RequestParam String ward,
                             @RequestParam String addressDetail, @RequestParam(required = false) String note,
                             @RequestParam String shippingMethod, @RequestParam String paymentMethod,
                             RedirectAttributes redirectAttributes) {

        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            return "redirect:/auth";
        }

        List<CartItem> cartItems = cartService.getCartItems(loggedInCustomer);
        if (cartItems.isEmpty()) {
            redirectAttributes.addFlashAttribute("cartErrorMessage", "Giỏ hàng trống, không thể đặt hàng.");
            return "redirect:/cart";
        }

        double discountAmount = (double) Optional.ofNullable(session.getAttribute("appliedDiscountAmount")).orElse(0.0);


        try {
             double subtotal = cartService.getSubtotal(loggedInCustomer);
             double shippingFee = (subtotal >= 100000 || subtotal == 0) ? 0 : 30000;

            Order order = orderService.placeOrder(loggedInCustomer, cartItems,
                    fullName, phone, email, city, district, ward, addressDetail,
                    note, shippingMethod, paymentMethod, shippingFee, discountAmount);

             session.removeAttribute("appliedDiscountAmount");
             session.removeAttribute("appliedDiscountCode");

            return "redirect:/checkout/success/" + order.getId();

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("checkoutError", "Đặt hàng thất bại: " + e.getMessage());
            redirectAttributes.addFlashAttribute("oldEmail", email);
            redirectAttributes.addFlashAttribute("oldFullName", fullName);
            redirectAttributes.addFlashAttribute("oldPhone", phone);
            redirectAttributes.addFlashAttribute("oldCity", city);
            redirectAttributes.addFlashAttribute("oldDistrict", district);
            redirectAttributes.addFlashAttribute("oldWard", ward);
            redirectAttributes.addFlashAttribute("oldAddressDetail", addressDetail);
            redirectAttributes.addFlashAttribute("oldNote", note);
            redirectAttributes.addFlashAttribute("oldShippingMethod", shippingMethod);
            redirectAttributes.addFlashAttribute("oldPaymentMethod", paymentMethod);
            redirectAttributes.addFlashAttribute("discountAmount", discountAmount);
            redirectAttributes.addFlashAttribute("discountMessage", session.getAttribute("discountMessage")); 

            return "redirect:/checkout";
        }
    }

    @GetMapping("/success/{orderId}")
    public String orderSuccess(@PathVariable Long orderId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
         Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
         if (loggedInCustomer == null) {
             return "redirect:/auth";
         }

         Optional<Order> orderOpt = orderService.findOrderById(orderId);

         if (orderOpt.isPresent() && orderOpt.get().getCustomer().getCustomerId().equals(loggedInCustomer.getCustomerId())) {
             model.addAttribute("order", orderOpt.get());
             return "order-success"; 
         } else {
             redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy đơn hàng hoặc bạn không có quyền xem đơn hàng này.");
             return "redirect:/"; 
         }
    }
}