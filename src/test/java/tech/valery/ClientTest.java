package tech.valery;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void ShouldReturnFullNameInOneString_WhenIsAcquired(){
        Client client = new Client("John", "Doe", new Date());
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void ShouldGetSpecification(){
        Client client = new Client("John", "Doe", new Date(100));
        assertThat(new ClientSpecification("John", "Doe", new Date(100))).isEqualToComparingFieldByField(client.getSpecification());
    }
}
