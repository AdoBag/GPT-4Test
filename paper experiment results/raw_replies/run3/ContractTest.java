```java
package org.example.run1;

import org.example.dependencies.UnderComp;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    @Test
    void testDefaultConstructor() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
        assertEquals(0.0, contract.m_strike, 0.001);
        assertFalse(contract.m_includeExpired);
    }

    @Test
    void testParameterizedConstructor() {
        Vector comboLegs = new Vector();
        Contract contract = new Contract(100, "AAPL", "STK", "202312", 150.0, "CALL", "100", "NASDAQ", "USD", "AAPL",
                comboLegs, "NASDAQ", false, "CUSIP", "037833100");

        assertEquals(100, contract.m_conId);
        assertEquals("AAPL", contract.m_symbol);
        assertEquals("STK", contract.m_secType);
        assertEquals("202312", contract.m_expiry);
        assertEquals(150.0, contract.m_strike, 0.001);
        assertEquals("CALL", contract.m_right);
        assertEquals("100", contract.m_multiplier);
        assertEquals("NASDAQ", contract.m_exchange);
        assertEquals("USD", contract.m_currency);
        assertEquals("AAPL", contract.m_localSymbol);
        assertEquals(comboLegs, contract.m_comboLegs);
        assertEquals("NASDAQ", contract.m_primaryExch);
        assertFalse(contract.m_includeExpired);
        assertEquals("CUSIP", contract.m_secIdType);
        assertEquals("037833100", contract.m_secId);
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("Leg1");
        Contract original = new Contract(100, "AAPL", "STK", "202312", 150.0, "CALL", "100", "NASDAQ", "USD", "AAPL",
                comboLegs, "NASDAQ", false, "CUSIP", "037833100");
        
        Contract clone = (Contract) original.clone();
        
        assertNotSame(original, clone);
        assertEquals(original, clone);
        assertNotSame(original.m_comboLegs, clone.m_comboLegs);
        assertEquals(original.m_comboLegs, clone.m_comboLegs);
    }

    @Test
    void testEquals() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("Leg1");
        Contract contract1 = new Contract(100, "AAPL", "STK", "202312", 150.0, "CALL", "100", "NASDAQ", "USD", "AAPL",
                comboLegs1, "NASDAQ", false, "CUSIP", "037833100");

        Vector comboLegs2 = new Vector();
        comboLegs2.add("Leg1");
        Contract contract2 = new Contract(100, "AAPL", "STK", "202312", 150.0, "CALL", "100", "NASDAQ", "USD", "AAPL",
                comboLegs2, "NASDAQ", false, "CUSIP", "037833100");

        Vector comboLegs3 = new Vector();
        Contract contract3 = new Contract(101, "AAPL", "STK", "202312", 150.0, "CALL", "100", "NASDAQ", "USD", "AAPL",
                comboLegs3, "NASDAQ", false, "CUSIP", "037833100");

        assertEquals(contract1, contract1); // Test for equality with itself
        assertEquals(contract1, contract2); // Test for equality with another instance with the same values
        assertNotEquals(contract1, contract3); // Test for inequality with another instance with different values
        assertNotEquals(contract1, null); // Test for inequality with null
        assertNotEquals(contract1, new Object()); // Test for inequality with an object of a different class
    }
}
```