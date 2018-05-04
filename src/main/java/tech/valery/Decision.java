package tech.valery;

public class Decision {
    private final ClientRequest clientRequest;
    private final boolean isPositiveDecision;

    public Decision(ClientRequest clientRequest, boolean isPositiveDecision) {
        this.clientRequest = clientRequest;
        this.isPositiveDecision = isPositiveDecision;
    }

    public boolean isPositiveDecision() {
        return isPositiveDecision;
    }
}
