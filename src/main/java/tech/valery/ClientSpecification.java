package tech.valery;

import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

public class ClientSpecification {

    public static final BiFunction<ClientSpecification, ClientSpecification, Boolean> clientSpecEqualsPredicate = (ClientSpecification currentSpec, ClientSpecification actualSpec) ->{
        boolean isEqual = true;
        if(!currentSpec.lastName.equals(actualSpec.lastName)) isEqual = false;
        if(!currentSpec.firstName.equals(actualSpec.firstName)) isEqual = false;
        if(currentSpec.age != actualSpec.age) isEqual = false;
        if(!currentSpec.passportNumber.equals(actualSpec.passportNumber)) isEqual = false;

        return isEqual;
    };

    public final String firstName;
    public final String lastName;
    public final String passportNumber;

    public final int age;

    public ClientSpecification(String firstName, String lastName, int age, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.passportNumber = passportNumber;
    }
}
