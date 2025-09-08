public class TestUser {
    public static void main(String[] args) {
        User u = new User("U001", "Nguyen Van A", "a@example.com", "0901234567", "Hanoi");
        System.out.println("[OK] " + u);
        
        try {
            new User("U002", "B", "sai-email", "123", "");
            System.out.println("[FAIL] Không bắt lỗi email/phone");
        } catch (IllegalArgumentException ex) {
            System.out.println("[EXPECTED] " + ex.getMessage());
        }
    }
}
