public class TestNNCollection {
    public static void main(String[] args) {
        NNCollection danhBa = new NNCollection();

        danhBa.insert(new NameNumber("Nguyen", "0901-111-222"));
        danhBa.insert(new NameNumber("Tran", "0902-222-333"));
        danhBa.insert(new NameNumber("Le", "0903-333-444"));

        System.out.println("Danh ba:");
        for (NameNumber nn : danhBa.getList()) {
            System.out.println(nn);
        }

        String search = "Tran";
        String number = danhBa.findNumber(search);
        if (number != null) {
            System.out.println("So cua " + search + ": " + number);
        } else {
            System.out.println("Khong tim " + search);
        }
    }
}
