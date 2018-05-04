package tech.valery;

import java.math.BigDecimal;

public class CreditCard {
    private final int clientId;
    private BigDecimal currentMoneyAmount;
    private BigDecimal percents;

    public CreditCard(int clientId, BigDecimal currentMoneyAmount, BigDecimal percents) {
        this.clientId = clientId;
        this.currentMoneyAmount = currentMoneyAmount;
        this.percents = percents;
    }
}
