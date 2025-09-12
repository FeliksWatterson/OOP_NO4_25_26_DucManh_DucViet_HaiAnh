public class TestNNCollection {
    public static void main(String[] args) {
        NNCollection book = new NNCollection();

        book.insert(new NameNumber("Nguyen", "0901.111.222"));
        book.insert(new NameNumber("Tran",   "0901.111.333"));
        book.insert(new NameNumber("Le",     "0901.111.444"));

        System.out.println("Tong so = " + book.size());
        System.out.println("Find Nguyen -> " + book.findNumber("nguyen"));
        System.out.println("Find Pham   -> " + book.findNumber("Pham"));
    }
}
