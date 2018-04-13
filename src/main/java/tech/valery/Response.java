package tech.valery;

public class Response {
    private final ClientRequest clientRequest;
    private final boolean isPositiveDecision;

    public Response(ClientRequest clientRequest, boolean isPositiveDecision) {
        this.clientRequest = clientRequest;
        this.isPositiveDecision = isPositiveDecision;
    }
}
