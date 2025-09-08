import java.util.List;

public class TestRecursion {
    public static void main(String[] args) {
        System.out.println("5! = " + Recursion.factorial(5));
        System.out.println("F(10) = " + Recursion.fibonacci(10));
        System.out.println("gcd(84, 18) = " + Recursion.gcd(84, 18));
        System.out.println("2^20 = " + Recursion.power(2, 20));
        System.out.println("reverse('Spencer') = " + Recursion.reverse("Spencer"));
        int[] a = {1,2,3,4,5};
        System.out.println("sum = " + Recursion.sum(a));
        List<String> moves = Recursion.hanoi(3, 'A', 'B', 'C');
        for (String mv : moves) System.out.println(mv);
    }
}
