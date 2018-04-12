package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.valery.simsystem.Person;

public class BranchTest {
    @Test
    public void ShouldHandleRequest_WhenTaskIsRegistered(){
        Branch branch = new Branch();

        Person person = new Person();
        ClientRequest clientRequest = new ClientRequest(person, 100000, 24);

        branch.handleRequest(clientRequest);

        Assertions.assertTrue(branch.getHandledRequests().contains(clientRequest));

    }
}
