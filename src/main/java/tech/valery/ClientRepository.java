package tech.valery;

import java.util.Optional;

public interface ClientRepository {
    Client getClient(int id);
    Client getClient(ClientSpecification clientSpecification);

    void registerClient(Client client);
}
