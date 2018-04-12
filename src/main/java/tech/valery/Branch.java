package tech.valery;

import java.util.Date;

public class Branch {

    private ClientRepository dataProvider;

    public void registerClient(Client client) {
        dataProvider.registerClient(client);
    }

    public Branch(ClientRepository dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Client findClient(int id) {
        return dataProvider.getClient(id);
    }
}
