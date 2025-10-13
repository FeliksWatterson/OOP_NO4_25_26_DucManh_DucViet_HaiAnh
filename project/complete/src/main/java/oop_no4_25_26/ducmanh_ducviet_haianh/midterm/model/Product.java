package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

public class Product {
    private String id;
    private String name;
    private String category;
    private long unitPrice;

    public Product() {}
    public Product(String id, String name, String category, long unitPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public long getUnitPrice() { return unitPrice; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setUnitPrice(long unitPrice) { this.unitPrice = unitPrice; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category + " | " + unitPrice + "₫";
    }
}
