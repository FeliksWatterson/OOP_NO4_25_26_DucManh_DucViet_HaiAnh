package DucManh;

import java.util.Random;

public class RandNum {
    public static int generate() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public static double generateDouble() {
        Random r = new Random();
        return r.nextDouble() * 1000;
    }

    public static void main(String[] args) {
        System.out.println("Random int: " + generate());
        System.out.println("Random double: " + generateDouble());
    }
}
