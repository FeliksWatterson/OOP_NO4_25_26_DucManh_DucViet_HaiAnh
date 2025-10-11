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

    // Getter
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // ✅ Thêm Setter (để có thể cập nhật thông tin khách hàng)
    public void setFullName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty())
            this.fullName = fullName;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@"))
            this.email = email;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.matches("\\d{9,11}")) // chỉ chấp nhận số 9–11 chữ số
            this.phone = phone;
    }

    // ✅ Hiển thị chi tiết thông tin khách hàng
    public void displayInfo() {
        System.out.printf("ID: %s | Tên: %s | Email: %s | SĐT: %s%n",
                id, fullName, email, phone);
    }

    @Override
    public String toString() {
        return id + " - " + fullName + " (" + phone + ")";
    }
}
