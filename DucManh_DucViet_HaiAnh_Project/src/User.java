import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

public class User {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;

    private static final Pattern EMAIL_RX =
            Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_RX =
            Pattern.compile("^[0-9+() -]{6,20}$");

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public User(String id, String fullName, String email, String phone, String address) {
        this();
        setId(id);
        setFullName(fullName);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }


    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id không được rỗng");
        this.id = id.trim();
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Họ tên không được rỗng");
        this.fullName = fullName.trim();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !EMAIL_RX.matcher(email).matches())
            throw new IllegalArgumentException("Email không hợp lệ");
        this.email = email.trim();
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        if (phone == null || !PHONE_RX.matcher(phone).matches())
            throw new IllegalArgumentException("SĐT không hợp lệ");
        this.phone = phone.trim();
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        this.address = address == null ? "" : address.trim();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    @Override public String toString() {
        return "User{id='%s', fullName='%s', email='%s', phone='%s', address='%s', createdAt=%s}"
                .formatted(id, fullName, email, phone, address, createdAt);
    }
}
