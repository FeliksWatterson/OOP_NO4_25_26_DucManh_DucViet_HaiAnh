public class Callee {
    private int i = 0;
    private void incr() {
        i++;
        System.out.println("Callee increments i to " + i);
    }

    private class Closure implements Incrementable {
        @Override
        public void increment() { incr(); }
    }

    public Incrementable getCallbackReference() {
        return new Closure();
    }
}
