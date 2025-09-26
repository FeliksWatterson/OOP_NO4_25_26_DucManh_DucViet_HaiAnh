public class TestSequence {
    public static void main(String[] args) {
        Sequence s = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            s.add(Integer.toString(i));
        }

        Selector sl = s.getSelector();
        while (!sl.end()) {
            System.out.println(sl.current());
            sl.next();
        }
    }
}
