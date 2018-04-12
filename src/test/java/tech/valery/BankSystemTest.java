package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BankSystemTest {
    @Test
    public void ShouldReturnClient_WhenThatClientIsAdded(){

        BankSystem bankSystem = new BankSystem(new ConcurrentClientRepository());

        Client client = new Client("A", "A", new Date(1));

        bankSystem.registerClient(client);
        Client findedClient = bankSystem.findClient(client.getId());

        //todo assertequals in spec
        Assertions.assertTrue(findedClient.equals(client));
    }
}
