package tech.valery.participants;

public class PersonDTO {

    public final int age;
    public final String firstName;
    public final String lastName;

    public PersonDTO(String firstName, String lastName, int age) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
