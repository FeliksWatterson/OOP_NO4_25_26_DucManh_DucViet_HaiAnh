import java.time.LocalTime;
import java.util.Objects;

public class Time implements Comparable<Time> {
    private int h, m, s;

    public Time(int h, int m, int s) {
        if (!isValid(h, m, s)) throw new IllegalArgumentException("Thời gian không hợp lệ");
        this.h = h; this.m = m; this.s = s;
    }

    public static boolean isValid(int h, int m, int s) {
        return (0 <= h && h < 24) && (0 <= m && m < 60) && (0 <= s && s < 60);
    }

    public static Time now() {
        LocalTime t = LocalTime.now();
        return new Time(t.getHour(), t.getMinute(), t.getSecond());
    }

    public static Time parse(String hhmmss) {
        Objects.requireNonNull(hhmmss);
        String[] p = hhmmss.trim().split(":");
        if (p.length != 3) throw new IllegalArgumentException("Định dạng phải HH:mm:ss");
        return new Time(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
    }

    public int toSecondOfDay() { return h * 3600 + m * 60 + s; }

    public Time plusSeconds(long delta) {
        long total = (toSecondOfDay() + (delta % 86400) + 86400) % 86400;
        int H = (int) (total / 3600);
        int M = (int) ((total % 3600) / 60);
        int S = (int) (total % 60);
        return new Time(H, M, S);
    }

    public Time minusSeconds(long delta) { return plusSeconds(-delta); }

    public String to24hString() { return String.format("%02d:%02d:%02d", h, m, s); }

    public String to12hString() {
        int hh = h % 12 == 0 ? 12 : h % 12;
        String ampm = h < 12 ? "AM" : "PM";
        return String.format("%02d:%02d:%02d %s", hh, m, s, ampm);
    }

    public static int differenceInSeconds(Time a, Time b) {
        return Math.abs(a.toSecondOfDay() - b.toSecondOfDay());
    }

    public int getHour() { return h; }
    public int getMinute() { return m; }
    public int getSecond() { return s; }

    @Override public int compareTo(Time o) {
        return Integer.compare(this.toSecondOfDay(), o.toSecondOfDay());
    }

    @Override public String toString() { return to24hString(); }
}
