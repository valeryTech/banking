package tech.valery.participants;

import java.math.BigDecimal;

public class CreditHistory {
    public final BigDecimal totalAmountOfTakenCredits;
    public final boolean isPaingWasDelayed;
    public final int currentDebtNumber;

    public CreditHistory(BigDecimal totalAmountOfTakenCredits, boolean isPaingWasDelayed, int currentDebtNumber) {
        this.totalAmountOfTakenCredits = totalAmountOfTakenCredits;
        this.isPaingWasDelayed = isPaingWasDelayed;
        this.currentDebtNumber = currentDebtNumber;
    }
}
