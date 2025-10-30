package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.*;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.OrderRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.OrderItemRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.ProductRepository; // Import thêm
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces.AddressInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService; 

    @Autowired
    private AddressInterface addressService; 

    @Autowired
    private ProductRepository productRepository; 

    @Transactional 
    public Order placeOrder(Customer customer, List<CartItem> cartItems,
                            String fullName, String phone, String email,
                            String city, String district, String ward, String addressDetail,
                            String note, String shippingMethod, String paymentMethod,
                            double shippingFee, double discountAmount) {

        if (cartItems == null || cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống, không thể đặt hàng.");
        }

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + cartItem.getProduct().getId()));
            
            if (product.getStock() == null || product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Sản phẩm '" + product.getName() + "' không đủ số lượng tồn kho (Chỉ còn " + product.getStock() + ").");
            }
        }


        Order order = new Order();
        order.setCustomer(customer);
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setEmail(email);
        String fullAddress = addressDetail + ", " + ward + ", " + district + ", " + city;
        order.setShippingAddress(fullAddress);
        order.setNote(note);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingMethod(shippingMethod);
        order.setPaymentMethod(paymentMethod);
        order.setStatus("Đặt hàng thành công"); 

        double subtotal = cartItems.stream().mapToDouble(CartItem::getSubtotal).sum();
        order.setShippingFee(shippingFee);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(subtotal + shippingFee - discountAmount);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItemsList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            OrderItem orderItem = new OrderItem(
                    savedOrder,
                    product,
                    cartItem.getQuantity(),
                    product.getPrice()
            );
            orderItemsList.add(orderItem);

            Product productToUpdate = productRepository.findById(product.getId()).get();
            int newStock = productToUpdate.getStock() - cartItem.getQuantity();
            productToUpdate.setStock(newStock);
            productRepository.save(productToUpdate);
        }
        orderItemRepository.saveAll(orderItemsList);
        savedOrder.setOrderItems(orderItemsList);

        cartService.clearCart(customer);

        return savedOrder;
    }

     public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomerOrderByOrderDateDesc(customer);
     }

     public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
     }
}