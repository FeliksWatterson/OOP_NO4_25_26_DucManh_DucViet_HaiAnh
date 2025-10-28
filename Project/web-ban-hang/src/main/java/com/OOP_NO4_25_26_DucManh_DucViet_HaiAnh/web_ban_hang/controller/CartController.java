package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.CartItem; 
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.ProductService;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.CartService; 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService; 

    @Autowired
    private ProductService productService; 
    private static final String CUSTOMER_SESSION_KEY = "loggedInCustomer";

     @GetMapping
    public String showCartPage(HttpSession session, Model model) {
        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        List<CartItem> cartItems;
        int cartItemCount;
        double subtotal;

        if (loggedInCustomer != null) {
            cartItems = cartService.getCartItems(loggedInCustomer);
            cartItemCount = cartService.getTotalItems(loggedInCustomer); 
            subtotal = cartService.getSubtotal(loggedInCustomer); 
        } else {
            cartItems = List.of(); 
            cartItemCount = 0;
            subtotal = 0.0;
            model.addAttribute("loginRequiredMessage", "Vui lòng đăng nhập để xem hoặc thêm sản phẩm vào giỏ hàng.");
        }

        double shippingFee = (subtotal >= 100000 || subtotal == 0) ? 0 : 30000;
        double discount = 0;
        if (model.containsAttribute("discountAmount")) {
             discount = (double) model.getAttribute("discountAmount");
        }
        double total = subtotal + shippingFee - discount;


        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartItemCount", cartItemCount);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("discount", discount);
        model.addAttribute("total", total);
        return "cart";
    }

     @PostMapping("/add")
     public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                            @RequestParam(value = "returnUrl", required = false) String returnUrl,
                            HttpServletRequest request,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng.");
            String targetUrl = "/product/" + productId;
            return "redirect:/auth";
        }

        try {
            cartService.addItem(loggedInCustomer, productId, quantity);
             Optional<Product> productOpt = productService.findProductById(productId); 
             String productName = productOpt.map(Product::getName).orElse("Sản phẩm");
            redirectAttributes.addFlashAttribute("cartSuccessMessage", "Đã thêm '" + productName + "' vào giỏ hàng!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("cartErrorMessage", e.getMessage());
        }

        String redirectTarget;
        if (returnUrl != null && !returnUrl.isEmpty()) {
            redirectTarget = "redirect:" + returnUrl;
        } else {
            redirectTarget = "redirect:/product/" + productId;
        }
        return redirectTarget;
    }

     @PostMapping("/update")
     public String updateCartItem(@RequestParam("productId") Long productId,
                                  @RequestParam("quantity") int quantity,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {

        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phiên đăng nhập hết hạn. Vui lòng đăng nhập lại.");
            return "redirect:/auth";
        }

        try {
            CartItem updatedItem = cartService.updateItemQuantity(loggedInCustomer, productId, quantity);
             if (updatedItem != null) { 
                redirectAttributes.addFlashAttribute("cartSuccessMessage", "Cập nhật số lượng cho '" + updatedItem.getProduct().getName() + "'.");
             } else { 
                 Optional<Product> productOpt = productService.findProductById(productId);
                 String productName = productOpt.map(Product::getName).orElse("Sản phẩm");
                 redirectAttributes.addFlashAttribute("cartSuccessMessage", "Đã xóa '" + productName + "' khỏi giỏ hàng.");
             }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("cartErrorMessage", e.getMessage());
        }

        return "redirect:/cart"; 
     }

     @PostMapping("/remove")
     public String removeFromCart(@RequestParam("productId") Long productId,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {

        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phiên đăng nhập hết hạn. Vui lòng đăng nhập lại.");
            return "redirect:/auth";
        }

         String productName = "Sản phẩm"; 
         try{
              Optional<Product> productOpt = productService.findProductById(productId);
              if (productOpt.isPresent()) {
                  productName = productOpt.get().getName();
              }
             cartService.removeItem(loggedInCustomer, productId);
             redirectAttributes.addFlashAttribute("cartSuccessMessage", "Đã xóa '" + productName + "' khỏi giỏ hàng.");
         } catch (RuntimeException e) {
              redirectAttributes.addFlashAttribute("cartErrorMessage", "Lỗi khi xóa sản phẩm: " + e.getMessage());
         }

        return "redirect:/cart"; 
     }

     @PostMapping("/discount")
    public String applyDiscount(@RequestParam("discountCode") String code, RedirectAttributes redirectAttributes) {
        boolean isValid = code.equalsIgnoreCase("GIAM10");
        double discountAmount = isValid ? 10000 : 0;

        if (isValid) {
            redirectAttributes.addFlashAttribute("discountAmount", discountAmount);
            redirectAttributes.addFlashAttribute("appliedDiscountCode", code);
            redirectAttributes.addFlashAttribute("cartSuccessMessage", "Áp dụng mã giảm giá thành công!");
        } else {
             redirectAttributes.addFlashAttribute("discountAmount", 0.0);
             redirectAttributes.addFlashAttribute("appliedDiscountCode", code);
            redirectAttributes.addFlashAttribute("cartErrorMessage", "Mã giảm giá không hợp lệ hoặc đã hết hạn.");
        }
        return "redirect:/cart";
    }

     @GetMapping("/checkout")
    public String proceedToCheckout(HttpSession session, RedirectAttributes redirectAttributes) {

        Customer loggedInCustomer = (Customer) session.getAttribute(CUSTOMER_SESSION_KEY);
        if (loggedInCustomer == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng đăng nhập để tiến hành thanh toán.");
            session.setAttribute("redirectAfterLogin", "/cart/checkout"); 
            return "redirect:/auth";
        }

        List<CartItem> cartItems = cartService.getCartItems(loggedInCustomer);
        if (cartItems.isEmpty()) {
            redirectAttributes.addFlashAttribute("cartErrorMessage", "Giỏ hàng trống, không thể thanh toán.");
            return "redirect:/cart";
        }
        System.out.println("Chuyển đến thanh toán với " + cartService.getTotalItems(loggedInCustomer) + " sản phẩm.");
        return "redirect:/"; 
    }
}