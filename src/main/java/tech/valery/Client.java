package tech.valery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    private static AtomicInteger idCounter = new AtomicInteger();

    private int id;

    private String firstName;
    private String lastName;

    //todo create Builder pattern
    private Date birthDate;

    public Client(String firstName, String lastName, Date birthDate) {
        this.birthDate = birthDate;

        id = idCounter.addAndGet(1);

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
