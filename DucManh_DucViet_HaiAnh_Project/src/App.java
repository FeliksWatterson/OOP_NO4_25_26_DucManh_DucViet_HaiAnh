public class App {
    public static void main(String[] args) {
        System.out.println("TestUse:");
        TestUser.main(new String[]{});

        System.out.println("TestTime:");
        TestTime.main(new String[]{});

        System.out.println("TestRecursion:");
        TestRecursion.main(new String[]{});

        System.out.println("TestSequence:");
        TestSequence.main(new String[]{}); 

        System.out.println("TestCallback");
        TestCallback.main(new String[]{});
    }
}
