package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BankSystemTest {
    @Test
    public void ShouldReturnClient_WhenThatClientIsAdded(){

        BankSystem bankSystem = new BankSystem(new ConcurrentClientRepository());

        Client client = new Client("A", "A", 20, "1100 0000");

        bankSystem.registerClient(client);
        Client findedClient = bankSystem.findClient(client.getId());

        assertThat(client).isEqualToComparingFieldByField(findedClient);
    }
}
