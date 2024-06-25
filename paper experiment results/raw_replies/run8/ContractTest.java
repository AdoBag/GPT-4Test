```java
package org.example.run1;

import org.example.dependencies.UnderComp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    private Contract contract1;
    private Contract contract2;

    @BeforeEach
    void setUp() {
        contract1 = new Contract(1, "AAPL", "STK", "20230120", 150.0, "C", "100", "SMART", "USD", "AAPL",
                new Vector(), "ISLAND", false, "CUSIP", "037833100");
        contract2 = new Contract(1, "AAPL", "STK", "20230120", 150.0, "C", "100", "SMART", "USD", "AAPL",
                new Vector(), "ISLAND", false, "CUSIP", "037833100");
    }

    @Test
    void testEquals_SameObject() {
        assertEquals(contract1, contract1);
    }

    @Test
    void testEquals_EquivalentObject() {
        assertEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_Null() {
        assertNotEquals(contract1, null);
    }

    @Test
    void testNotEquals_DifferentClass() {
        assertNotEquals(contract1, new String("Different Class"));
    }

    @Test
    void testNotEquals_DifferentConId() {
        contract2.m_conId = 2;
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentSecType() {
        contract2.m_secType = "OPT";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentSymbol() {
        contract2.m_symbol = "MSFT";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentExchange() {
        contract2.m_exchange = "NASDAQ";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentPrimaryExch() {
        contract2.m_primaryExch = "BATS";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentCurrency() {
        contract2.m_currency = "EUR";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentStrike() {
        contract2.m_strike = 200.0;
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentExpiry() {
        contract2.m_expiry = "20240120";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentRight() {
        contract2.m_right = "P";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentMultiplier() {
        contract2.m_multiplier = "200";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentLocalSymbol() {
        contract2.m_localSymbol = "AAPL_20240120_C150";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentSecIdType() {
        contract2.m_secIdType = "SEDOL";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testNotEquals_DifferentSecId() {
        contract2.m_secId = "012345678";
        assertNotEquals(contract1, contract2);
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        Contract clone = (Contract) contract1.clone();
        assertEquals(contract1, clone);
    }
}
```