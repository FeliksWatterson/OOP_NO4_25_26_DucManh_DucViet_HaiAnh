package Callback;
public class Caller {
    private final Incrementable callbackRef;

    public Caller(Incrementable cb) {
        this.callbackRef = cb;
    }

    public void go() {
        callbackRef.increment();
    }
}

