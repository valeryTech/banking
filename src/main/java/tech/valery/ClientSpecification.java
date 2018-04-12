package tech.valery;

import java.util.Date;
import java.util.Map;

public class ClientSpecification {
    public final String firstName;
    public final String lastName;

    public final Date birthDate;

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
