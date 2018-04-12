package tech.valery;

import tech.valery.simsystem.Person;

public class ClientRequest {
    public final Person person;
    public final double sum2Credit;
    public final int months;
    public ClientRequest(Person person, double sum2Credit, int months) {
        this.person = person;
        this.sum2Credit = sum2Credit;
        this.months = months;
    }
}
