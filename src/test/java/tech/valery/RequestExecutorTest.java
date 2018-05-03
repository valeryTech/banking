package tech.valery;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.valery.extentions.MockitoExtension;
import tech.valery.participants.AntiFraudService;
import tech.valery.participants.CreditBureau;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RequestExecutorTest {

    @Mock
    private static ClientRepository clientRepository;

    @Mock
    private static AntiFraudService antiFraudService;

    @Mock
    private static CreditBureau creditBureau;

    @BeforeAll
    static void initAll() {
        MockitoAnnotations.initMocks(BankSystemTest.class);
    }

    @Test
    void ShouldWait2Tasks_WhenRunInAsync(){

        BankSystem bankSystem = new BankSystem(clientRepository, antiFraudService, creditBureau);

        ClientSpecification currentClientSpec = new ClientSpecification("Ivan", "Petrov", 23, "4400 125020");

        CompletableFuture<Boolean> doublesCheckFuture = bankSystem.getClientDoubleStatusAsync(currentClientSpec);
        CompletableFuture<Boolean> stopListCheckFuture = bankSystem.getStopListCheckAsync(currentClientSpec);
        //job?

        Boolean toContinue = !doublesCheckFuture.join() && stopListCheckFuture.join();

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
