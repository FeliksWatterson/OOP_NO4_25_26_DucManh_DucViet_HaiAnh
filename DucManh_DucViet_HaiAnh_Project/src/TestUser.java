public class TestUser {
    public static void main(String[] args) {
        User u = new User("U001", "Nguyen Van A", "a@example.com");
        System.out.println(u);

        u.setFullName("Nguyen Van B");
        System.out.println("Tên mới: " + u.getFullName());
    }
}
