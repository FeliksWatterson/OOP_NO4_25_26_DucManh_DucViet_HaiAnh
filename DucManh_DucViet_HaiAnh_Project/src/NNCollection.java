import java.util.ArrayList;
import java.util.List;

public class NNCollection {
    private final List<NameNumber> data = new ArrayList<>();

    public NNCollection() {}

    public void insert(NameNumber nn) {
        data.add(nn);
    }

    public String findNumber(String lastName) {
        for (NameNumber nn : data) {
            if (nn.getLastName().equalsIgnoreCase(lastName)) {
                return nn.getPhone();
            }
        }
        return null;
    }

    public int size() { return data.size(); }
}
