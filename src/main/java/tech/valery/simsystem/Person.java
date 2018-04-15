package tech.valery.simsystem;

import tech.valery.participants.PersonDTO;

public class Person {

    private int age;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, int age) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonDTO toDTO() {
        return new PersonDTO(firstName, lastName, age);
    }
}
