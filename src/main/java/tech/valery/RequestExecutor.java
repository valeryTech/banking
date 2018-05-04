package tech.valery;

import tech.valery.participants.CreditHistory;
import tech.valery.simsystem.Person;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static tech.valery.BankSystem.MAX_TIMEOUT;
import static tech.valery.BankSystem.NEGATIVE_DOUBLE_CHECK_ANSWER;

public class RequestExecutor implements Callable<Decision> {

    private final ClientRequest clientRequest;

    private final BankSystem bankSystem;

    RequestExecutor(ClientRequest clientRequest, BankSystem bankSystem) {
        this.clientRequest = clientRequest;
        this.bankSystem = bankSystem;
    }

    private Decision handleCreditCardRequest() {

        //request type

        //input data

        //analysis

        Person person = clientRequest.person;

        final ClientSpecification currentClientSpec = new ClientSpecification(person.getFirstName(),
                person.getLastName(), person.getAge(), person.getPassportNumber());

        CompletableFuture<Boolean> doublesCheckFuture = bankSystem.getClientDoubleStatusAsync(currentClientSpec)
                .handle((result, ex) -> result != null ? result : true)
                .completeOnTimeout(NEGATIVE_DOUBLE_CHECK_ANSWER, MAX_TIMEOUT, TimeUnit.MILLISECONDS);

        CompletableFuture<Boolean> stopListCheckFuture = bankSystem.getStopListCheckAsync(currentClientSpec)
                .completeOnTimeout(false, MAX_TIMEOUT, TimeUnit.MILLISECONDS);

        CompletableFuture<Boolean> antiFroudCheckFuture = bankSystem.getAntifraudCheckAsync(currentClientSpec)
                .completeOnTimeout(false, MAX_TIMEOUT, TimeUnit.MILLISECONDS);

        Boolean toContinue = !doublesCheckFuture.join() && stopListCheckFuture.join();

        //decision 1
        final Decision negativeDecision = new Decision(clientRequest, false);
        if (!toContinue) {
            return negativeDecision;
        }

        CompletableFuture<CreditHistory> creditHistoryFuture = bankSystem.getCreditHistoryAsync(currentClientSpec);
        //exceptions

        CreditHistory creditHistory = creditHistoryFuture.join();
        //decision 2

        CompletableFuture<Boolean> sbCheckedFuture = bankSystem.checkInSBAsync(currentClientSpec, creditHistory);

        // final check
        Boolean isClientClearBySS = sbCheckedFuture.join();
        Boolean isClientClearByAntiFraudService = antiFroudCheckFuture.join();

        if(!(isClientClearByAntiFraudService && isClientClearBySS)){
            return negativeDecision;
        }


        //output

        //proceed to processing

        return new Decision(clientRequest, true);
    }

    @Override
    public Decision call() {
        return handleCreditCardRequest();
    }
}
