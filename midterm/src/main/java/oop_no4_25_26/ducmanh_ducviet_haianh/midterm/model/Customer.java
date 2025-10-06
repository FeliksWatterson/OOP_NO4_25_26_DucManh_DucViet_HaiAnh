package oop_no4_25_26.ducmanh_ducviet_haianh.midterm.model;

public class Customer {
    private final String id;
    private String fullName;
    private String email;
    private String phone;

    public Customer(String id, String fullName, String email, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override public String toString() {
        return id + " - " + fullName + " <" + email + "> (" + phone + ")";
    }
}
