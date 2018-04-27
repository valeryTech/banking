package tech.valery;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

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

    @Override
    public Client getClient(ClientSpecification searchSpecification) {

        BiFunction<ClientSpecification, ClientSpecification, Boolean> searchFunc = ClientSpecification.clientSpecEqualsPredicate;

        Client client = clients.search(1, (k, currentClient) -> {
            return searchFunc.apply(currentClient.getSpecification(), searchSpecification) ? currentClient : null;
        });

        return client;
    }
}
