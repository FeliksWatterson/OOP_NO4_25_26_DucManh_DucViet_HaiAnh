import java.util.ArrayList;
import java.util.List;

public class NNCollection {
    private final List<NameNumber> list;

    public NNCollection() {
        this.list = new ArrayList<>();
    }

    public NNCollection(List<NameNumber> initial) {
        this.list = initial;
    }

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

    public List<NameNumber> getList() {
        return list;
    }
}
