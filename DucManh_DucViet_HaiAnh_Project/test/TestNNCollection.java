public class TestNNCollection {
    public static void main(String[] args) {
        NNCollection phoneBook = new NNCollection();

        phoneBook.insert(new NameNumber("Nguyen", "0901-111-222"));
        phoneBook.insert(new NameNumber("Tran", "0902-222-333"));
        phoneBook.insert(new NameNumber("Le", "0903-333-444"));

        System.out.println("Danh ba:");
        for (NameNumber nn : phoneBook.getList()) {
            System.out.println(nn);
        }

        String search = "Tran";
        String number = phoneBook.findNumber(search);
        if (number != null) {
            System.out.println("So cua " + search + ": " + number);
        } else {
            System.out.println("Khong tim thay " + search);
        }
    }
}
