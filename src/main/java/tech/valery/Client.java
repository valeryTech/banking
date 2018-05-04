package tech.valery;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    private static final AtomicInteger idCounter = new AtomicInteger();

    private final int id;

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

    private final String firstName;
    private final String lastName;

    private final int age;

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
