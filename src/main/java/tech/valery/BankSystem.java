package tech.valery;

import java.util.Date;

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
}
