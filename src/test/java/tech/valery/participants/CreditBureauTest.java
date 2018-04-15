package tech.valery.participants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.valery.simsystem.Person;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditBureauTest {
    @Test
    void ShouldGetCreditHistory_WhenPersonParametersHasPassed(){

        CreditBureau creditBureau = mock(CreditBureau.class);
        when(creditBureau.getCreditHistory(any(PersonDTO.class))).thenReturn(new CreditHistory(new BigDecimal(1000), true, 1));

        Person person = new Person("Doe","John", 10);

        CreditHistory actualCreditHistory = creditBureau.getCreditHistory(person.toDTO());
        CreditHistory expectedCreditHistory = new CreditHistory(new BigDecimal(1000), true, 1);

        assertThat(actualCreditHistory).isEqualToComparingFieldByField(expectedCreditHistory);
    }
}
