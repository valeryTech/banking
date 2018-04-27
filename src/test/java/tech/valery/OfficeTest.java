package tech.valery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.valery.participants.AntiFraudService;
import tech.valery.participants.CreditBureau;
import tech.valery.simsystem.Person;

import static org.mockito.Mockito.mock;

public class OfficeTest {
    @Test
    public void ShouldHandleRequest_WhenTaskIsRegistered() {
        AntiFraudService antiFraudService = mock(AntiFraudService.class);
        CreditBureau creditBureau = mock(CreditBureau.class);
        //todo mock
        BankSystem bankSystem = new BankSystem(new ConcurrentClientRepository(), antiFraudService, creditBureau);

        Office office = new Office(1, bankSystem);

        Person person = new Person.PersonBuilder("John", "NY", "NY").lastName("Doe")
                .age(20)
                .isEmployed(true)
                .isFemale(false)
                .createPerson();
        ClientRequest clientRequest = new ClientRequest(person, 100000, 24);

        office.handleRequest(clientRequest);

        Assertions.assertTrue(office.isRequestIsProcessed(clientRequest));
    }
}
