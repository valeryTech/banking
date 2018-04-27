package tech.valery;

import tech.valery.participants.*;

import java.util.Optional;

public class BankSystem {

    private final ClientRepository dataProvider;
    private final AntiFraudService antiFraudService;
    private final CreditBureau creditBureau;

    public void registerClient(Client client) {
        dataProvider.registerClient(client);
    }

    public BankSystem(ClientRepository dataProvider, AntiFraudService antiFraudService, CreditBureau creditBureau) {
        this.dataProvider = dataProvider;
        this.antiFraudService = antiFraudService;
        this.creditBureau = creditBureau;
    }

    public Client findClient(int id) {
        return dataProvider.getClient(id);
    }

    public Client findClient(ClientSpecification clientSpecification) {
        return dataProvider.getClient(clientSpecification);
    }

    public boolean isClientAlreadyInBase(ClientSpecification clientSpecification) {
        //todo
        return findClient(clientSpecification) != null;
    }

    public Boolean checkForFraud(ClientSpecification spec) {
        ClientFraundCheckDTO clientDTO = new ClientFraundCheckDTO(spec.firstName, spec.lastName,spec.passportNumber, spec.age);
        return antiFraudService.isFraud(clientDTO);
    }

    public CreditHistory getCreditHistory(ClientSpecification spec) {
        return creditBureau.getCreditHistory(new PersonDTO(spec.firstName, spec.lastName, spec.age));
    }

    public Boolean checkInSB(ClientSpecification spec, CreditHistory creditHistory) {
        return true;
    }
}
