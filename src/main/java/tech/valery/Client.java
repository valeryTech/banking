package tech.valery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    private static AtomicInteger idCounter = new AtomicInteger();

    private int id;

    public Client(ClientSpecification spec) {
        this.firstName = spec.firstName;
        this.lastName = spec.lastName;
        this.age = spec.age;
        this.passportNumber = spec.passportNumber;

        id = idCounter.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    private String firstName;
    private String lastName;

    private int age;

    private final String passportNumber;

    public Client(String firstName, String lastName, int age, String passportNumber) {

        id = idCounter.getAndIncrement();

        this.firstName = firstName;
        this.lastName = lastName;

        this.age = age;

        this.passportNumber = passportNumber;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public ClientSpecification getSpecification() {
        return new ClientSpecification(firstName, lastName, age, passportNumber);
    }
}
