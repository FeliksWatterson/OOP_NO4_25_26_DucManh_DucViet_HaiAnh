package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

public class Product {
    private String id;
    private String name;
    private String category;
    private long unitPrice;

    public Product(String id, String name, String category, long unitPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return name + " - " + category + " (" + unitPrice + "₫)";
    }
}
