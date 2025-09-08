public class TestTime {
    public static void main(String[] args) {
        Time t1 = new Time(23, 59, 50);
        Time t2 = t1.plusSeconds(15); 
        System.out.println("t1 = " + t1.to24hString());
        System.out.println("t2 = " + t2.to24hString() + " (" + t2.to12hString() + ")");
        System.out.println("diff(s) = " + Time.differenceInSeconds(t1, t2));

        Time t3 = Time.parse("07:30:00");
        System.out.println("t3 valid? " + Time.isValid(t3.getHour(), t3.getMinute(), t3.getSecond()));
        System.out.println("now = " + Time.now());
    }
}
