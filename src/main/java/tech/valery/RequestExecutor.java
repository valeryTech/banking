package tech.valery;

import java.util.concurrent.Callable;

public class RequestExecutor implements Callable<Response> {

    private final ClientRequest clientRequest;

    RequestExecutor(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }

    private Response handleRequest(){

        //request type

        //input data

        //analysis

        //todo check doubles



        //output

        //proceed to processing

        return new Response(clientRequest, true);
    }

    @Override
    public Response call() throws Exception {
        return handleRequest();
    }
}
