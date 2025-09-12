import java.util.ArrayList;

public class NNCollection {
    private ArrayList<NameNumber> list = new ArrayList<>();
    public void insert(NameNumber nn) {
        list.add(nn);
    }

    public String findNumber(String lastName) {
        for (NameNumber nn : list) {
            if (nn.getLastName().equalsIgnoreCase(lastName)) {
                return nn.getPhone();
            }
        }
        return null; 
    }

    public void printAll() {
        for (NameNumber nn : list) {
            System.out.println(nn);
        }
    }
}
