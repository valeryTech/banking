package tech.valery;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void ShouldReturnNameInOneString_WhenIsAcquired(){
        Client client = new Client("John", "Doe", new Date());
        // todo assertj
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void ShouldGetSpecification(){
        Client client = new Client("John", "Doe", new Date());
        assertTrue(new ClientSpecification("John", "Doe", new Date()).isEqual(client.getSpecification()));
    }
}
