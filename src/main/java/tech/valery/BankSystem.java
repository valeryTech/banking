package tech.valery;

import java.util.Date;
import java.util.Optional;

public class BankSystem {

    private ClientRepository dataProvider;

    public void registerClient(Client client) {
        dataProvider.registerClient(client);
    }

    public BankSystem(ClientRepository dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Client findClient(int id) {
        return dataProvider.getClient(id);
    }

    public Optional<Client> findClient(ClientSpecification clientSpecification) {
        return dataProvider.getClient(clientSpecification);
    }
}
