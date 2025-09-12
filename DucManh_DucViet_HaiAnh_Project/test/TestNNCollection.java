public class TestNNCollection {
    public static void main(String[] args) {
        NNCollection book = new NNCollection();

        book.insert(new NameNumber("Nguyen", "0901-111-222"));
        book.insert(new NameNumber("Tran",   "0902-222-333"));
        book.insert(new NameNumber("Le",     "0903-333-444"));

        System.out.println("Danh ba:");
        book.printAll();

        String search = "Tran";
        String result = book.findNumber(search);
        if (result != null) {
            System.out.println("So cua " + search + " la: " + result);
        } else {
            System.out.println("Khong tim thay " + search);
        }
    }
}
