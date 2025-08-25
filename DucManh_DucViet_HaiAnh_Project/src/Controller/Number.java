package Controller;

public class Number {
    public int i;

    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();

        n1.i = 2;
        n2.i = 5;

        n1 = n2;        // từ đây n1 và n2 cùng trỏ tới 1 object
        n2.i = 10;
        System.out.println(n2.i); // in ra 10

        n1.i = 20;
        System.out.println(n1.i); // in ra 20
    }
}
