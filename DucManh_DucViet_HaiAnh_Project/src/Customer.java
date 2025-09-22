import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String phone;

    public Customer(String id, String name, String email, String phone) {
        setId(id);
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    public String getId() { return id; }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer id cannot be blank");
        }
        this.id = id.trim();
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        this.name = name.trim();
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        this.email = email.trim();
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be blank");
        }
        this.phone = phone.trim();
    }

    public boolean matchesKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return false;
        }
        String lower = keyword.toLowerCase(Locale.ROOT);
        return name.toLowerCase(Locale.ROOT).contains(lower)
            || email.toLowerCase(Locale.ROOT).contains(lower)
            || phone.toLowerCase(Locale.ROOT).contains(lower);
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + email + " - " + phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equalsIgnoreCase(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.toLowerCase(Locale.ROOT));
    }
}
