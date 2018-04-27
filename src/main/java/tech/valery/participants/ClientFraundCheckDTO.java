package tech.valery.participants;

public class ClientFraundCheckDTO {
    public final String firstName;
    public final String lastName;
    public final String passportNumber;

    public final int age;

    public ClientFraundCheckDTO(String firstName, String lastName, String passportNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.age = age;
    }
}
