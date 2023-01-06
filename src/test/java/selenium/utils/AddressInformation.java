package selenium.utils;

public enum AddressInformation {
    ADDRESS_1("Robin", "Hood", "715200"),
    ADDRESS_2("Sherlock", "Holmes", "319200");

    private final String firstName;
    private final String lastName;
    private final String zipOrPostalCode;

    AddressInformation(String firstName, String lastName, String zipOrPostalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipOrPostalCode = zipOrPostalCode;
    }

    public String getZipOrPostalCode() {
        return zipOrPostalCode;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
