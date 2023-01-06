package restAssured.utils;

public enum BookingCustomer {

    FIRST_CUSTOMER("Albert", "Einstein");

    private final String firstName;
    private final String lastName;

    BookingCustomer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
