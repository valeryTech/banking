package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import tech.valery.participants.AntiFraudService;
import tech.valery.participants.CreditBureau;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankSystemTest {

    @Test
    public void ShouldReturnClient_WhenThatClientIsAdded(){

        AntiFraudService antiFraudService = mock(AntiFraudService.class);
        CreditBureau creditBureau = mock(CreditBureau.class);

        //todo mock
        BankSystem bankSystem = new BankSystem(new ConcurrentClientRepository(), antiFraudService, creditBureau);

        Client client = new Client("A", "A", 20, "1100 0000");

        bankSystem.registerClient(client);
        Client findedClient = bankSystem.findClient(client.getId());

        assertThat(client).isEqualToComparingFieldByField(findedClient);
    }

    @Test
    void ShouldReturnFalse_WhenClientIsNotInBase(){

        ClientRepository clientRepository = mock(ClientRepository.class);
        when(clientRepository.getClient(any(ClientSpecification.class))).thenReturn(null);

        System.out.println();

        BankSystem bankSystem = new BankSystem(clientRepository, mock(AntiFraudService.class), mock(CreditBureau.class));

        Assertions.assertTrue(!bankSystem.isClientAlreadyInBase(null));
    }

    @Test
    void ShouldRecoverDoublesCheckToFalse_WhenTimeoutIsExceeded(){

    }
}
