package tech.valery;

public interface ClientRepository {
    Client getClient(int id);

    void registerClient(Client client);
}
