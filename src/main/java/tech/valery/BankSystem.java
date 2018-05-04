package tech.valery;

import tech.valery.participants.*;

import java.util.concurrent.CompletableFuture;

public class BankSystem {

    public static final int MAX_TIMEOUT = 1000;

    public static final boolean NEGATIVE_DOUBLE_CHECK_ANSWER = true;

    private final ClientRepository clientProvider;
    private final AntiFraudService antiFraudService;
    private final CreditBureau creditBureau;

    public void registerClient(Client client) {
        clientProvider.registerClient(client);
    }

    public BankSystem(ClientRepository clientProvider, AntiFraudService antiFraudService, CreditBureau creditBureau) {
        this.clientProvider = clientProvider;
        this.antiFraudService = antiFraudService;
        this.creditBureau = creditBureau;
    }

    public Client findClient(int id) {
        return clientProvider.getClient(id);
    }

    public Client findClient(ClientSpecification clientSpecification) {
        return clientProvider.getClient(clientSpecification);
    }

    public boolean isClientAlreadyInBase(ClientSpecification clientSpecification) {
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
        });

        return clientDoubleStatusFuture;
    }

    public CompletableFuture<Boolean> getStopListCheckAsync(ClientSpecification currentClientSpec) {
        return CompletableFuture
                .supplyAsync(() -> currentClientSpec.age > 24);
    }


    public Boolean checkForFraud(ClientSpecification spec) {
        ClientFraundCheckDTO clientDTO = new ClientFraundCheckDTO(spec.firstName, spec.lastName, spec.passportNumber, spec.age);
        return antiFraudService.isFraud(clientDTO);
    }

    public CompletableFuture<CreditHistory> getCreditHistoryAsync(ClientSpecification spec) {

        return CompletableFuture.supplyAsync(() -> creditBureau.getCreditHistory(new PersonDTO(spec.firstName, spec.lastName, spec.age)));
    }

    public CompletableFuture<Boolean> checkInSBAsync(ClientSpecification spec, CreditHistory creditHistory) {
        return CompletableFuture.completedFuture(true);
    }

    public CompletableFuture<Boolean> getAntifraudCheckAsync(ClientSpecification spec) {
        ClientFraundCheckDTO dto = new ClientFraundCheckDTO(spec.firstName, spec.lastName, spec.passportNumber, spec.age);
        return CompletableFuture.supplyAsync(() -> antiFraudService.isFraud(dto));
    }
}
