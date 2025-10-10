package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

public class OrderItem {
    private final Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }

    public long getSubTotal() { return product.getUnitPrice() * quantity; }
}
    