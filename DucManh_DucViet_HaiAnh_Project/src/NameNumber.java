public class NameNumber {
    private String lastName;
    private String Phone;

    public NameNumber(String lastName, String Phone) {
        this.lastName = lastName;
        this.Phone = Phone;
    }

    public String getLastName() { return lastName; }
    public String getPhone() { return Phone; }

    @Override
    public String toString() {
        return lastName + " -> " + Phone;
    }
}
 
