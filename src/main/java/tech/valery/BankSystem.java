package tech.valery;

import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import tech.valery.participants.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class BankSystem {

    public static final int MAX_TIMEOUT = 1000;

    public static final boolean NEGATIVE_DOUBLE_CHECK_ANSWER = true;

    private final ClientRepository dataProvider;
    private final AntiFraudService antiFraudService;
    private final CreditBureau creditBureau;

    public void registerClient(Client client) {
        dataProvider.registerClient(client);
    }

    public BankSystem(ClientRepository dataProvider, AntiFraudService antiFraudService, CreditBureau creditBureau) {
        this.dataProvider = dataProvider;
        this.antiFraudService = antiFraudService;
        this.creditBureau = creditBureau;
    }

    public Client findClient(int id) {
        return dataProvider.getClient(id);
    }

    public Client findClient(ClientSpecification clientSpecification) {
        return dataProvider.getClient(clientSpecification);
    }

    public boolean isClientAlreadyInBase(ClientSpecification clientSpecification) {
        //todo
        return findClient(clientSpecification) != null;
    }

    public CompletableFuture<Boolean> getClientDoubleStatusAsync(ClientSpecification clientSpecification) {
        CompletableFuture<Boolean> doublesCheckFuture = new CompletableFuture<>();

        CompletableFuture<Boolean> clientDoubleStatusFuture = CompletableFuture.supplyAsync(() -> {
            Boolean result = null;
            try {
                result = findClient(clientSpecification) != null;
            } catch (Exception ex) {
                doublesCheckFuture.completeExceptionally(ex);
            }
            return result;
        })
                .handle((result, ex) -> result != null ? result : true)
                .completeOnTimeout(NEGATIVE_DOUBLE_CHECK_ANSWER, MAX_TIMEOUT, TimeUnit.MILLISECONDS);

        return clientDoubleStatusFuture;
    }

    public Boolean checkForFraud(ClientSpecification spec) {
        ClientFraundCheckDTO clientDTO = new ClientFraundCheckDTO(spec.firstName, spec.lastName, spec.passportNumber, spec.age);
        return antiFraudService.isFraud(clientDTO);
    }

    public CreditHistory getCreditHistory(ClientSpecification spec) {
        return creditBureau.getCreditHistory(new PersonDTO(spec.firstName, spec.lastName, spec.age));
    }

    public Boolean checkInSB(ClientSpecification spec, CreditHistory creditHistory) {
        return true;
    }
}
