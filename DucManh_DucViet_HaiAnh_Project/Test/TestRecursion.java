public class TestRecursion {
    public static void main(String[] args) {
        Recursion electronics = new Recursion("Electronics");
        electronics.addProduct("TV");
        electronics.addProduct("Radio");
        Recursion phones = new Recursion("Phones");
        phones.addProduct("iPhone");
        phones.addProduct("Samsung Galaxy");

        Recursion laptops = new Recursion("Laptops");
        laptops.addProduct("MacBook");
        laptops.addProduct("ThinkPad");

        electronics.addSubRecursion(phones);
        electronics.addSubRecursion(laptops);

        System.out.println("Tong so san pham trong Dien thoai/Laptop: " + electronics.countProducts());
        System.out.println("Co Samsung khong? " + electronics.containsProduct("Samsung Galaxy"));
        System.out.println("Có MacBook không? " + electronics.containsProduct("MacBook"));
    }
}
