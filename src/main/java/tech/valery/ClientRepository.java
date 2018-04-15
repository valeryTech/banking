package tech.valery;

import java.util.Optional;

public interface ClientRepository {
    Client getClient(int id);
    Optional<Client> getClient(ClientSpecification clientSpecification);

    void registerClient(Client client);
}
