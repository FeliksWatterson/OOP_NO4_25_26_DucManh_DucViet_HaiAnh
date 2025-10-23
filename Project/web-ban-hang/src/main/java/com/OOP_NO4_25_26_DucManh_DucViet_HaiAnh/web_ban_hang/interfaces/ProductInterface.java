package com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.interfaces;

import com.OOP_NO4_25_26_DucManh_DucViet_HaiAnh.web_ban_hang.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts(); // Cần để hiển thị danh sách sản phẩm trên trang chủ
    // Có thể thêm các phương thức khác như saveProduct, deleteProduct,... sau này
}