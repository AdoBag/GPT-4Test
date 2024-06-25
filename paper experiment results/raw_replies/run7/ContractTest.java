```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    private Contract contract1;
    private Contract contract2;
    private Vector comboLegs;

    @BeforeEach
    void setUp() {
        comboLegs = new Vector();

        contract1 = new Contract(1, "AAPL", "STK", "202212", 150.0, "C", "100",
                "NASDAQ", "USD", "AAPL", comboLegs, "ISLAND", false, "CUSIP", "037833100");

        contract2 = new Contract(1, "AAPL", "STK", "202212", 150.0, "C", "100",
                "NASDAQ", "USD", "AAPL", comboLegs, "ISLAND", false, "CUSIP", "037833100");
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        Contract clonedContract = (Contract) contract1.clone();
        assertEquals(contract1, clonedContract);
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(contract1.equals(contract1));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(contract1.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(contract1.equals("Some String"));
    }

    @Test
    void testEquals_DifferentConId() {
        contract2.m_conId = 2;
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentSymbol() {
        contract2.m_symbol = "GOOG";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentSecType() {
        contract2.m_secType = "OPT";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentExpiry() {
        contract2.m_expiry = "202301";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentStrike() {
        contract2.m_strike = 200.0;
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentRight() {
        contract2.m_right = "P";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentMultiplier() {
        contract2.m_multiplier = "50";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentExchange() {
        contract2.m_exchange = "NYSE";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentCurrency() {
        contract2.m_currency = "EUR";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentLocalSymbol() {
        contract2.m_localSymbol = "GOOGL";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentPrimaryExch() {
        contract2.m_primaryExch = "SMART";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentSecIdType() {
        contract2.m_secIdType = "ISIN";
        assertFalse(contract1.equals(contract2));
    }

    @Test
    void testEquals_DifferentSecId() {
        contract2.m_secId = "US0378331005";
        assertFalse(contract1.equals(contract2));
    }

    // Add more tests for combos and underComp if relevant
}
```