package tech.valery;

import org.junit.jupiter.api.Test;
import tech.valery.participants.AntiFraudService;
import tech.valery.participants.CreditBureau;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static org.mockito.Mockito.mock;

public class RequestExecutorTest {

    @Test
    void ShouldWait2Tasks_WhenRunInAsync(){

        AntiFraudService antiFraudService = mock(AntiFraudService.class);
        CreditBureau creditBureau = mock(CreditBureau.class);
        ClientRepository clientRepository = mock(ClientRepository.class);


        // TODO: 4/26/18 add functionality

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        ClientSpecification currentClientSpec = new ClientSpecification("Ivan", "Petrov", 25, "4400 125020");

        CompletableFuture<Boolean> doublesCheckFuture = CompletableFuture
                .supplyAsync(() -> bankSystem.findClient(currentClientSpec) != null);

        CompletableFuture<Boolean> stopListCheckFuture = CompletableFuture
                .supplyAsync(() -> currentClientSpec.age > 24);

        Boolean toContinue = !doublesCheckFuture.join() && stopListCheckFuture.join();

        if(!toContinue)

        System.out.println(toContinue);

//
//        CompletableFuture<Boolean> antiFroudCheckFututre = CompletableFuture.supplyAsync(() -> bankSystem.checkForFraud(currentClientSpec));
//
//        CompletableFuture.allOf(doublesCheckFuture, stopListCheckFuture)
//                .handle((f, t) -> {return null;})
//                .thenApply((dumb) -> {
//                    Boolean isDoubled = doublesCheckFuture.join();
//                    Boolean isPermitted = stopListCheckFuture.join();
//
//                    return true;
//                });
    }
}
