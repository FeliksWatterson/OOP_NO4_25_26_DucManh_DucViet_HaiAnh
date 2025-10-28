package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.controller;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Address;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Order;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services.OrderService;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.AddressInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account/address")
public class AddressController {

    @Autowired
    private AddressInterface addressService;

    @Autowired
    private OrderService orderService;


    @GetMapping
    public String showAddressesPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }

        List<Address> addresses = addressService.findAddressesByCustomer(customer);
        model.addAttribute("addresses", addresses);
        model.addAttribute("customer", customer); 
        model.addAttribute("addressCount", addresses.size()); 
        if (!model.containsAttribute("newAddress")) { 
             model.addAttribute("newAddress", new Address());
        }
        List<Order> orders = orderService.findOrdersByCustomer(customer);
        model.addAttribute("orders", orders);
        return "address"; 
    }

    @PostMapping("/add")
    public String addAddress(@ModelAttribute("newAddress") Address address, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }
        address.setCustomer(customer); 

        try {
            addressService.saveAddress(address);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm địa chỉ thành công!");
        } catch (Exception e) {
             redirectAttributes.addFlashAttribute("errorMessage", "Thêm địa chỉ thất bại: " + e.getMessage());
             redirectAttributes.addFlashAttribute("newAddress", address); 
        }
        return "redirect:/account/address";
    }

    @GetMapping("/edit/{id}")
    public String showEditAddressForm(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
         Customer customer = (Customer) session.getAttribute("loggedInCustomer");
         if (customer == null) {
             return "redirect:/auth";
         }
         Optional<Address> addressOpt = addressService.findByIdAndCustomer(id, customer);
         if (addressOpt.isPresent()) {
             model.addAttribute("editAddress", addressOpt.get());
             model.addAttribute("customer", customer); 
             model.addAttribute("addresses", addressService.findAddressesByCustomer(customer));
             model.addAttribute("addressCount", addressService.countAddressesByCustomer(customer));
             if (!model.containsAttribute("newAddress")) {
                model.addAttribute("newAddress", new Address());
            }
             List<Order> orders = orderService.findOrdersByCustomer(customer);
             model.addAttribute("orders", orders);
             return "address"; 
         } else {
             redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy địa chỉ.");
             return "redirect:/account/address";
         }
    }

     @PostMapping("/update/{id}")
    public String updateAddress(@PathVariable Long id, @ModelAttribute("editAddress") Address addressDetails, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }

        Optional<Address> addressOpt = addressService.findByIdAndCustomer(id, customer);
        if (addressOpt.isPresent()) {
            Address existingAddress = addressOpt.get();
            existingAddress.setFullName(addressDetails.getFullName());
            existingAddress.setPhone(addressDetails.getPhone());
            existingAddress.setAddressDetail(addressDetails.getAddressDetail());
            existingAddress.setDefault(addressDetails.isDefault());
            existingAddress.setCustomer(customer);

            try {
                addressService.saveAddress(existingAddress);
                redirectAttributes.addFlashAttribute("successMessage", "Cập nhật địa chỉ thành công!");
                return "redirect:/account/address";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật địa chỉ thất bại: " + e.getMessage());
                redirectAttributes.addFlashAttribute("editAddress", existingAddress); 
                return "redirect:/account/address/edit/" + id; 
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy địa chỉ.");
            return "redirect:/account/address";
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }
        try {
            addressService.deleteAddress(id, customer);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa địa chỉ thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/account/address";
    }

    @PostMapping("/setDefault/{id}")
    public String setDefaultAddress(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");
        if (customer == null) {
            return "redirect:/auth";
        }
        try {
            addressService.setDefaultAddress(id, customer);
            redirectAttributes.addFlashAttribute("successMessage", "Đặt địa chỉ mặc định thành công!");
        } catch (RuntimeException e) {
             redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/account/address";
    }
}