public class NameNumber {
    private String lastName;
    private String phone;

    public NameNumber() {}

    public NameNumber(String lastName, String phone) {
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "NameNumber{lastName='" + lastName + "', phone='" + phone + "'}";
    }
}
