package tech.valery;

import tech.valery.simsystem.Person;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Office {

    private final List<ClientRequest> processedRequests = new ArrayList<>();

    private final ExecutorService fixedThreadPool;

    /**
     * Creates Office with fixed number of managers
     *
     * @param managersNumber Managers works in the Office
     */
    public Office(int managersNumber) {
        fixedThreadPool = Executors.newFixedThreadPool(managersNumber);
    }

    public void handleRequest(ClientRequest clientRequest) {
        Future<Response> f = fixedThreadPool.submit(new RequestExecutor(clientRequest));

        try {
            Response r = f.get();
            processedRequests.add(clientRequest);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public boolean isRequestIsProcessed(ClientRequest request) {
        return processedRequests.contains(request);
    }
}
