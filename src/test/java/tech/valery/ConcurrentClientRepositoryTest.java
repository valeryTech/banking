package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.doesNotHave;

public class ConcurrentClientRepositoryTest {

    @Test
    public void ShouldReturnOneSpecifiedClient_WhenThatIsInRepository(){
        ClientRepository clientRepository = new ConcurrentClientRepository();

        ClientSpecification cs20 = new ClientSpecification("Johny", "Hue", 20, "2200 0010");

        clientRepository.registerClient(new Client("Johny", "Hue", 21, "2200 0010"));
        clientRepository.registerClient(new Client("Johny", "Hue", 20, "2200 0010"));

        ClientSpecification findedSpecification =clientRepository.getClient(cs20).getSpecification();
        assertThat(findedSpecification).isEqualToComparingFieldByField(cs20);
    }

    @Test
    public void ShouldReturnOptionalOfNull_WhenClientBySpecIsNotInCollection(){
        ClientRepository clientRepository = new ConcurrentClientRepository();

        ClientSpecification cs23 = new ClientSpecification("Johny", "Hue", 23, "2200 0010");

        clientRepository.registerClient(new Client("Johny", "Hue", 20, "2200 0010"));
        clientRepository.registerClient(new Client("Johny", "Hue", 21, "2200 0010"));


        Assertions.assertTrue(clientRepository.getClient(cs23) != null);
    }
}
