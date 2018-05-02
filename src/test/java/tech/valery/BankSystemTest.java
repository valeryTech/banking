package tech.valery;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.valery.extentions.MockitoExtension;
import tech.valery.participants.AntiFraudService;
import tech.valery.participants.CreditBureau;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BankSystemTest {

    @Mock
    private static ClientRepository clientRepository;

    @Mock
    private static AntiFraudService antiFraudService;

    @Mock
    private static CreditBureau creditBureau;

    @BeforeAll
    static void initAll() {
        MockitoAnnotations.initMocks(BankSystemTest.class);
    }

    @Test
    void ShouldReturnClient_WhenThatClientIsAdded() {

        BankSystem bankSystem = new BankSystem(new ConcurrentClientRepository(), antiFraudService, creditBureau);

        Client client = new Client("A", "A", 20, "1100 0000");

        bankSystem.registerClient(client);
        Client findedClient = bankSystem.findClient(client.getId());

        assertThat(client).isEqualToComparingFieldByField(findedClient);
    }

    @Test
    void ShouldReturnFalse_WhenClientIsNotInBase() {
        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        when(clientRepository.getClient(any(ClientSpecification.class))).thenReturn(null);

        assertFalse(bankSystem.isClientAlreadyInBase(mock(ClientSpecification.class)));
    }

    @Test
    void ShouldReturnTrue_WhenClientIsInBase() {
        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        when(clientRepository.getClient(any(ClientSpecification.class))).thenReturn(mock(Client.class));

        assertTrue(bankSystem.isClientAlreadyInBase(mock(ClientSpecification.class)));
    }

    @Test
    void ShouldGetTrue_WhenTimeoutIsExceeded() {

        when(clientRepository.getClient(any(ClientSpecification.class))).thenAnswer((invocation) -> {
            Thread.sleep(2000);
            return mock(Client.class);
        });

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        CompletableFuture<Boolean> doublesCheckFuture = CompletableFuture
                .supplyAsync(() -> bankSystem.findClient(mock(ClientSpecification.class)) != null)
                .completeOnTimeout(true, 1000, TimeUnit.MICROSECONDS);

        assertTrue(doublesCheckFuture.join());
    }


    @Test
    void GivenFutureWithExceptionHandler_WhenExceptionIsRaised_ThenRespondIsRecoveredToFalse() {
        when(clientRepository.getClient(any(ClientSpecification.class))).thenThrow(new IllegalStateException("404"));

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        CompletableFuture<Boolean> doublesCheckFuture = new CompletableFuture<>();

        CompletableFuture<Boolean> futureToHandle = CompletableFuture.supplyAsync(() -> {
            Boolean result = null;
            try {
                result = bankSystem.findClient(mock(ClientSpecification.class)) != null;
            } catch (Exception ex) {
                doublesCheckFuture.completeExceptionally(ex);
            }
            return result;
        });

        CompletableFuture<Boolean> handledFuture = futureToHandle.handle((result, ex) -> result != null ? result : false);

        assertFalse(handledFuture.join());
    }

    @Test
    void GivenBankSystem_WhenExceptionIsRaised_ThenRespondIsRecoveredToFalse() {
        when(clientRepository.getClient(any(ClientSpecification.class))).thenThrow(new IllegalStateException("404"));

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        CompletableFuture<Boolean> clientStatus = bankSystem.getClientDoubleStatusAsync(mock(ClientSpecification.class));
        //job

        assertTrue(clientStatus.join());

    }

    @Test
    void GivenBankSystem_WhenClientIsAbsent_ThenRespondIsFalse() {
        when(clientRepository.getClient(any(ClientSpecification.class))).thenReturn(null);

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        CompletableFuture<Boolean> clientStatus = bankSystem.getClientDoubleStatusAsync(mock(ClientSpecification.class));

        //job
        assertFalse(clientStatus.join());
    }

    @Test
    void GivenBankSystem_WhenClientIsFinded_ThenRespondIsTrue() {
        when(clientRepository.getClient(any(ClientSpecification.class))).thenReturn(mock(Client.class));

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        CompletableFuture<Boolean> clientStatus = bankSystem.getClientDoubleStatusAsync(mock(ClientSpecification.class));

        //job
        assertTrue(clientStatus.join());
    }

}