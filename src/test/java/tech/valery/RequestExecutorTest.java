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

}
