package tech.valery;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class ConcurrentClientRepository implements ClientRepository {

    private final ConcurrentHashMap<Integer, Client> clients;

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

        return clients.search(1, (k, currentClient) ->
                searchFunc.apply(currentClient.getSpecification(), searchSpecification) ? currentClient : null);
    }
}
