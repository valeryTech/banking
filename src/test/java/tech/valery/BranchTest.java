package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BranchTest {
    @Test
    public void ShouldReturnClient_WhenThatClientIsAdded(){

        Branch branch = new Branch(new ConcurrentClientRepository());

        Client client = new Client("A", "A", new Date(1));

        branch.registerClient(client);
        Client findedClient = branch.findClient(client.getId());

        //todo assertequals in spec
        Assertions.assertTrue(findedClient.equals(client));
    }
}
