public class NameNumber {
    private String lastName;
    private String phone;

    public NameNumber(String lastName, String phone) {
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return lastName + " -> " + phone;
    }
}
