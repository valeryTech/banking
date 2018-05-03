package tech.valery;

import tech.valery.simsystem.BankingSystem;
import tech.valery.simsystem.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Office {

    private final List<ClientRequest> processedRequests = new ArrayList<>();

    private final ExecutorService fixedThreadPool;

    private final BankSystem bankSystem;

    /**
     * Creates Office with fixed number of managers
     *
     * @param managersNumber Managers works in the Office
     */
    public Office(int managersNumber, BankSystem bankSystem) {
        fixedThreadPool = Executors.newFixedThreadPool(managersNumber);
        this.bankSystem = bankSystem;
    }

    public void requestForCreditCard(ClientRequest clientRequest) {
        Future<Response> f = fixedThreadPool.submit(new RequestExecutor(clientRequest, bankSystem));

        try {
            Response r = f.get();
            processedRequests.add(clientRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public boolean isRequestProcessed(ClientRequest request) {
        return processedRequests.contains(request);
    }

    public boolean clientHasCreditCard(Person person) {
        return true;
    }
}
