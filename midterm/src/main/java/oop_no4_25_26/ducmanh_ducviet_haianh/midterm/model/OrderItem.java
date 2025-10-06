package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

public class OrderItem {
    private final Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Số lượng phải > 0");
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Số lượng phải > 0");
        this.quantity = quantity;
    }

    public long getSubTotal() { return product.getUnitPrice() * quantity; }

    @Override public String toString() {
        return String.format("%s x %d = %,d VND", product.getName(), quantity, getSubTotal());
    }
}
