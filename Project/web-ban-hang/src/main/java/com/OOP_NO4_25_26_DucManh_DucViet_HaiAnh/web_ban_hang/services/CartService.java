package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.services;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.CartItem;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Customer;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.CartItemRepository;
import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.repository.ProductRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository; 

    public List<CartItem> getCartItems(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer không được null");
        }
        return cartItemRepository.findByCustomer(customer);
    }

    @Transactional 
    public CartItem addItem(Customer customer, Long productId, int quantity) {
        if (customer == null || productId == null || quantity <= 0) {
            throw new IllegalArgumentException("Thông tin không hợp lệ để thêm vào giỏ hàng.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId));
        Integer stock = product.getStock();
        if (stock != null && stock <= 0) {
             throw new RuntimeException("Sản phẩm '" + product.getName() + "' đã hết hàng.");
        }


        Optional<CartItem> existingItemOpt = cartItemRepository.findByCustomerAndProduct(customer, product);

        CartItem cartItem;
        if (existingItemOpt.isPresent()) {
            cartItem = existingItemOpt.get();
            int newQuantity = cartItem.getQuantity() + quantity;

             if (stock != null && newQuantity > stock) {
                 throw new RuntimeException("Không đủ số lượng tồn kho cho '" + product.getName() + "'. Chỉ còn " + (stock - cartItem.getQuantity()) + " sản phẩm có thể thêm.");
             }
            cartItem.setQuantity(newQuantity);
        } else {
             if (stock != null && quantity > stock) {
                 throw new RuntimeException("Không đủ số lượng tồn kho cho '" + product.getName() + "'. Chỉ còn " + stock + " sản phẩm.");
             }
            cartItem = new CartItem(customer, product, quantity);
        }

        return cartItemRepository.save(cartItem); 
    }

    @Transactional
    public CartItem updateItemQuantity(Customer customer, Long productId, int quantity) {
         if (customer == null || productId == null) {
            throw new IllegalArgumentException("Thông tin không hợp lệ để cập nhật giỏ hàng.");
         }
         if (quantity <= 0) {
             removeItem(customer, productId);
             return null;
         }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId));

        CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không có trong giỏ hàng của bạn."));

        Integer stock = product.getStock();
         if (stock != null && quantity > stock) {
             throw new RuntimeException("Không đủ số lượng tồn kho cho '" + product.getName() + "'. Tối đa: " + stock);
         }


        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void removeItem(Customer customer, Long productId) {
         if (customer == null || productId == null) {
            throw new IllegalArgumentException("Thông tin không hợp lệ để xóa khỏi giỏ hàng.");
         }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId));

        cartItemRepository.deleteByCustomerAndProduct(customer, product);
    }

    @Transactional
    public void clearCart(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer không được null");
        }
        cartItemRepository.deleteByCustomer(customer);
    }

    public int getTotalItems(Customer customer) {
        if (customer == null) return 0;
         Integer total = cartItemRepository.sumQuantityByCustomer(customer);
         return total != null ? total : 0;
    }

    public double getSubtotal(Customer customer) {
        if (customer == null) return 0.0;
        List<CartItem> items = getCartItems(customer);
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }
}