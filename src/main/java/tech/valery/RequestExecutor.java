package tech.valery;

import java.util.concurrent.Callable;

public class RequestExecutor implements Callable<Response> {

    private final ClientRequest clientRequest;

    private final BankSystem bankSystem;

    RequestExecutor(ClientRequest clientRequest, BankSystem bankSystem) {
        this.clientRequest = clientRequest;
        this.bankSystem = bankSystem;
    }

    private Response handleCreditCardRequest(){

        //request type

        //input data

        //analysis


        //create clientSpecification
        final ClientSpecification currentClientSpec = null;

        Callable<Boolean> checkDoubles = () -> {
            return !bankSystem.findClient(currentClientSpec).isPresent();
        };

        //output

        //proceed to processing

        return new Response(clientRequest, true);
    }

    @Override
    public Response call() throws Exception {
        return handleCreditCardRequest();
    }
}
