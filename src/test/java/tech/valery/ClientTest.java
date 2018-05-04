package tech.valery;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void ShouldReturnFullNameInOneString_WhenIsAcquired(){
        Client client = new Client("John", "Doe", 20, "1100 0000");
        assertEquals("John Doe", client.getName());
    }

    @Test
    public void ShouldGetSpecification(){
        Client client = new Client("John", "Doe", 20, "1100 0000");
        assertThat(new ClientSpecification("John", "Doe", 20, "1100 0000")).isEqualToComparingFieldByField(client.getSpecification());
    }
}
