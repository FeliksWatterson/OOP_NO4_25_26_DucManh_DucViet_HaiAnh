public class NameNumber {
    private String Ten;
    private String Phone;

    public NameNumber() {}

    public NameNumber(String Ten, String Phone) {
        this.Ten = Ten;
        this.Phone = Phone;
    }

    public String getTen() { return Ten; }
    public void setTen(String Ten) { this.Ten = Ten; }

    public String getPhone() { return Phone; }
    public void setPhone(String Phone) { this.Phone = Phone; }

    @Override
    public String toString() {
        return "NameNumber{Ten='" + Ten + "', Phone='" + Phone + "'}";
    }
}
