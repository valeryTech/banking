package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.valery.simsystem.Person;

public class OfficeTest {
    @Test
    public void ShouldHandleRequest_WhenTaskIsRegistered() {
        Office office = new Office(1);

        Person person = new Person();
        ClientRequest clientRequest = new ClientRequest(person, 100000, 24);

        office.handleRequest(clientRequest);

        Assertions.assertTrue(office.isRequestIsProcessed(clientRequest));
    }
}
