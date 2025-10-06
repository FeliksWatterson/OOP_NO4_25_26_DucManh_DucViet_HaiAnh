package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private final String id;
    private final Customer customer;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final List<OrderItem> items = new ArrayList<>();

    private static final double VAT = 0.08;
    private static final long DISCOUNT_THRESHOLD = 30_000_000L;
    private static final double DISCOUNT_RATE = 0.05;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }

    public void addItem(Product p, int qty) {
        for (OrderItem it : items) {
            if (it.getProduct().equals(p)) {
                it.setQuantity(it.getQuantity() + qty);
                return;
            }
        }
        items.add(new OrderItem(p, qty));
    }

    public long getSubTotal() {
        return items.stream().mapToLong(OrderItem::getSubTotal).sum();
    }

    public long getDiscount() {
        long sub = getSubTotal();
        return (sub >= DISCOUNT_THRESHOLD) ? Math.round(sub * DISCOUNT_RATE) : 0;
    }

    public long getTax() {
        long taxable = getSubTotal() - getDiscount();
        return Math.round(taxable * VAT);
    }

    public long getGrandTotal() {
        return getSubTotal() - getDiscount() + getTax();
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== HÓA ĐƠN BÁN HÀNG =====\n");
        sb.append("Mã đơn: ").append(id).append("\n");
        sb.append("Khách hàng: ").append(customer).append("\n");
        sb.append("Thời gian: ").append(createdAt).append("\n\n");

        sb.append("Danh sách mặt hàng:\n");
        for (OrderItem it : items) {
            sb.append(" - ").append(it.getProduct().getId())
              .append(" | ").append(it.getProduct().getName())
              .append(" | ").append(String.format("%,d", it.getProduct().getUnitPrice())).append(" VND")
              .append(" | SL: ").append(it.getQuantity())
              .append(" | Tạm tính: ").append(String.format("%,d", it.getSubTotal())).append(" VND\n");
        }

        sb.append("\nTổng hàng (SubTotal): ").append(String.format("%,d", getSubTotal())).append(" VND\n");
        sb.append("Giảm giá: ").append(String.format("%,d", getDiscount())).append(" VND\n");
        sb.append("Thuế VAT (8%): ").append(String.format("%,d", getTax())).append(" VND\n");
        sb.append(">>> THÀNH TIỀN: ").append(String.format("%,d", getGrandTotal())).append(" VND\n");
        sb.append("=============================\n");
        return sb.toString();
    }
}
