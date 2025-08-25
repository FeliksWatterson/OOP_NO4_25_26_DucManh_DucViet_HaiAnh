class Number {
    public int i;
}

public class Main {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();

        n1.i = 2;
        n2.i = 5;

        n1 = n2;      // giờ cả n1 và n2 cùng trỏ tới cùng 1 object trong heap

        n2.i = 10;    // thay đổi object đó → n1.i cũng = 10
        System.out.println(n2.i); // 10

        n1.i = 20;    // đổi qua n1 thì n2 cũng bị đổi
        System.out.println(n1.i); // 20
    }
}
