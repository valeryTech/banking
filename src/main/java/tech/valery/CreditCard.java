package tech.valery;

import java.math.BigDecimal;

public class CreditCard {
    private final int clientId;
    private BigDecimal currentMoneyAmount;
    private BigDecimal percents;

    public CreditCard(int clientId) {
        this.clientId = clientId;
    }
}
