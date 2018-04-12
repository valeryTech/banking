package tech.valery;

import java.util.Date;
import java.util.Map;

public class ClientSpecification {
    private final String firstName;
    private final String lastName;

    private final Date birthDate;

    public ClientSpecification(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public boolean isEqual(ClientSpecification specification) {
        if(this.firstName == specification.firstName && this.lastName == specification.lastName
                && this.birthDate.equals(specification.birthDate))
            return true;
        else
            return false;
    }


}
