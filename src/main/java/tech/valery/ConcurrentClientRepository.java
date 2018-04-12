package tech.valery;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentClientRepository implements ClientRepository {

    private ConcurrentHashMap<Integer, Client> clients;

    public ConcurrentClientRepository() {
        clients = new ConcurrentHashMap<>();
    }


    @Override
    public Client getClient(int id) {
        return clients.get(id);
    }

    @Override
    public void registerClient(Client client) {
        clients.put(client.getId(), client);
    }
}
