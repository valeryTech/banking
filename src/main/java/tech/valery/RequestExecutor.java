package tech.valery;

import tech.valery.participants.CreditHistory;
import tech.valery.simsystem.Person;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RequestExecutor implements Callable<Response> {

    private final ClientRequest clientRequest;

    private final BankSystem bankSystem;

    RequestExecutor(ClientRequest clientRequest, BankSystem bankSystem) {
        this.clientRequest = clientRequest;
        this.bankSystem = bankSystem;
    }

    private Response handleCreditCardRequest() {

        //request type

        //input data

        //analysis

        Person person = clientRequest.person;

        //create clientSpecification
        final ClientSpecification currentClientSpec = new ClientSpecification(person.getFirstName(),
                person.getLastName(), person.getAge(), person.getPassportNumber());

        Callable<Boolean> doublesCheck = () -> bankSystem.findClient(currentClientSpec) != null;


        Callable<Boolean> fraudCheck = () -> bankSystem.checkForFraud(currentClientSpec);

        Callable<CreditHistory> getCreditHistory = () -> bankSystem.getCreditHistory(currentClientSpec);


        CreditHistory creditHistory = null;
        Callable<Boolean> checkInSB = () -> bankSystem.checkInSB(currentClientSpec, creditHistory);

        Function<ClientSpecification, Boolean> stopListCheck = (spec) -> spec.age > 24;


        CompletableFuture<Boolean> doublesCheckFuture = CompletableFuture.supplyAsync(() -> bankSystem.findClient(currentClientSpec) != null);
        CompletableFuture<Boolean> stopListCheckFuture = CompletableFuture.supplyAsync(() -> stopListCheck.apply(currentClientSpec));
        CompletableFuture<Boolean> antiFroudCheckFututre = CompletableFuture.supplyAsync(() -> bankSystem.checkForFraud(currentClientSpec));

        CompletableFuture.allOf(doublesCheckFuture, stopListCheckFuture)
                .handle((f, t) -> {return null;})
                .thenApply((dumb) -> {
                    Boolean isDoubled = doublesCheckFuture.join();
                    Boolean isPermitted = stopListCheckFuture.join();

                    return true;
                });

        //decision

        //output

        //proceed to processing

        return new Response(clientRequest, true);
    }

    @Override
    public Response call() throws Exception {
        return handleCreditCardRequest();
    }
}
