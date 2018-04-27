package tech.valery.participants;

public interface AntiFraudService {
    boolean isFraud(ClientFraundCheckDTO fraudSpecification);
}
