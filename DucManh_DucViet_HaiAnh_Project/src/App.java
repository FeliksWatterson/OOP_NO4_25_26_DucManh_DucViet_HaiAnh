public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        bia b1 = new bia();

        bia b2 = new bia("Hanoi", "lap xanh", "bia");

        bia b3 = new bia("Heniken xanh");

        System.out.println("b1 = " + b1.nhanbia);
        System.out.println("b2 = " + b2.nhanbia);//duy: Hanoi
        System.out.println("b3 = " + b3.nhanbia + b3.lapbia);//duy: Hanoi

    }
}
