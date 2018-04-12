package tech.valery;

import tech.valery.simsystem.Person;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Branch {

    private List<ClientRequest> processedRequests = new ArrayList<>();

    public void handleRequest(ClientRequest clientRequest) {
        processedRequests.add(clientRequest);
    }

    public List<ClientRequest> getHandledRequests() {
        return processedRequests;
    }
}
