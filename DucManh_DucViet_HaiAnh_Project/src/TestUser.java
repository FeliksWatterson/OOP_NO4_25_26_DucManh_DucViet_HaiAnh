public class TestUser {
    public static void main(String[] args) {
        User u = new User("spencer", "12345");

        System.out.println(u);

        u.setpassword("67890");
        System.out.println("Password mới: " + u.getpassword());

        System.out.println(u.forgotpassword());
    }
}
