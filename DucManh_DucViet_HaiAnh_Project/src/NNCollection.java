import java.util.ArrayList;
import java.util.List;

public class NNCollection {
    private final List<NameNumber> data = new ArrayList<>();

    public NNCollection() {}

    public void insert(NameNumber nn) {
        data.add(nn);
    }

    public String findNumber(String Ten) {
        for (NameNumber nn : data) {
            if (nn.getTen().equalsIgnoreCase(Ten)) {
                return nn.getPhone();
            }
        }
        return null;
    }

    public int size() { return data.size(); }
}
