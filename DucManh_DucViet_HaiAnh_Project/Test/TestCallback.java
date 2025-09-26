public class TestCallback {
    public static void main(String[] args) {
        System.out.println("Le Hoang Duc Manh - 23010456");
        Callee c = new Callee();
        Caller caller = new Caller(c.getCallbackReference());

        for (int j = 0; j < 10; j++) {
            caller.go();
        }
    }
}
