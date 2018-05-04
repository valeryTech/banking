package tech.valery;

public interface ClientRepository {
    Client getClient(int id);
    Client getClient(ClientSpecification clientSpecification);

    void registerClient(Client client);
}
