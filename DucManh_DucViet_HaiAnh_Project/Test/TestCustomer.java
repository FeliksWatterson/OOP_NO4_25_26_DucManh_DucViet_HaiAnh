import java.util.*;

class Customer {
    private String id, name, email, phone;
    
    public Customer(String id, String name, String email, String phone) {
        this.id = id; this.name = name; this.email = email; this.phone = phone;
    }
    
    public String getId() { return id; }
    
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}

public class TestCustomer {
    private List<Customer> customers = new ArrayList<>();
    
    public void add(Customer c) { customers.add(c); }
    public List<Customer> getAll() { return customers; }
    public Customer findById(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }
    public void remove(String id) { customers.removeIf(c -> c.getId().equals(id)); }
    
    public static void main(String[] args) {
        System.out.println("Starting TestCustomer...");
        
        TestCustomer repo = new TestCustomer();
        
        repo.add(new Customer("C01", "Nguyen Van A", "a@gmail.com", "0901234567"));
        repo.add(new Customer("C02", "Le Thi B", "b@gmail.com", "0902345678"));
        
        System.out.println("Khach hang: ");
        for (Customer c : repo.getAll()) {
            System.out.println(c);
        }
        
        System.out.println("\nTim C02:");
        System.out.println(repo.findById("C02"));
        
        repo.remove("C01");
        System.out.println("\nSau khi xoa C01:");
        for (Customer c : repo.getAll()) {
            System.out.println(c);
        }
    }
}