package tech.valery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    private static AtomicInteger idCounter = new AtomicInteger();

    private int id;

    public Client(ClientSpecification spec) {
        this.firstName = spec.firstName;
        this.lastName = spec.lastName;
        this.birthDate = spec.birthDate;

        id = idCounter.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    private String firstName;
    private String lastName;

    private Date birthDate;

    public Client(String firstName, String lastName, Date birthDate) {
        this.birthDate = birthDate;

        id = idCounter.getAndIncrement();

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public ClientSpecification getSpecification() {
        return new ClientSpecification(this.firstName, this.lastName, this.birthDate);
    }
}
