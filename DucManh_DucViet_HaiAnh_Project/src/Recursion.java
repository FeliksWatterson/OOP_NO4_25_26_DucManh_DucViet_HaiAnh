import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }

    public static long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        long[] dp = new long[n + 2];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    public static int gcd(int a, int b) {
        return (b == 0) ? Math.abs(a) : gcd(b, a % b);
    }

    public static long power(long a, int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        if (n == 0) return 1;
        long half = power(a, n / 2);
        long prod = half * half;
        return (n % 2 == 0) ? prod : prod * a;
    }

    public static int sum(int[] arr) {
        return sumHelper(arr, 0);
    }

    private static int sumHelper(int[] arr, int i) {
        if (i == arr.length) return 0;
        return arr[i] + sumHelper(arr, i + 1);
    }

    public static String reverse(String s) {
        if (s == null || s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }

    public static List<String> hanoi(int n, char from, char aux, char to) {
        List<String> res = new ArrayList<>();
        hanoiRec(n, from, aux, to, res);
        return res;
    }

    private static void hanoiRec(int n, char from, char aux, char to, List<String> out) {
        if (n == 0) return;
        hanoiRec(n - 1, from, to, aux, out);
        out.add("Move disk " + n + " from " + from + " -> " + to);
        hanoiRec(n - 1, aux, from, to, out);
    }
}
