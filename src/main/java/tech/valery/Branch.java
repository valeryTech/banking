package tech.valery;

import tech.valery.simsystem.Person;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Branch {

    private List<ClientRequest> processedRequests = new ArrayList<>();

    private static final int MANAGERS_NUMBER = 10;

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(MANAGERS_NUMBER);

    public void handleRequest(ClientRequest clientRequest) {

        Future<Response> f =  fixedThreadPool.submit(new RequestExecutor(clientRequest));

        try {
            Response r = f.get();
            processedRequests.add(clientRequest);
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<ClientRequest> getHandledRequests() {
        return processedRequests;
    }
}
