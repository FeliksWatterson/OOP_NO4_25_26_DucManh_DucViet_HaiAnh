public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time() {
        setTime(0, 0, 0);
    }

    public Time(int h) {
        setTime(h, 0, 0);
    }

    public Time(int h, int m) {
        setTime(h, m, 0);
    }

    public Time(int h, int m, int s) {
        setTime(h, m, s);
    }

    public Time setTime(int h, int m, int s) {
        setHour(h);
        setMinute(m);
        setSecond(s);
        return this;
    }

    public Time setHour(int h) {
        hour = (h >= 0 && h < 24) ? h : 0;
        return this;
    }

    public Time setMinute(int m) {
        minute = (m >= 0 && m < 60) ? m : 0;
        return this;
    }

    public Time setSecond(int s) {
        second = (s >= 0 && s < 60) ? s : 0;
        return this;
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public int getSecond() { return second; }

    @Override
    public String toString() {
        int displayHour = (hour == 0 || hour == 12) ? 12 : hour % 12;
        String ampm = (hour < 12) ? "AM" : "PM";
        return displayHour + ":" +
               (minute < 10 ? "0" : "") + minute + ":" +
               (second < 10 ? "0" : "") + second + " " + ampm;
    }
}
