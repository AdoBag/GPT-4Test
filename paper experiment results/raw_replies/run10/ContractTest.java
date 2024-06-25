```java
package org.example.run1;

import org.example.dependencies.UnderComp;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    @Test
    void testContractDefaultConstructor() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
        assertEquals(0.0, contract.m_strike, 0.00001);
        assertEquals(false, contract.m_includeExpired);
        assertNull(contract.m_symbol);
        assertNull(contract.m_secType);
        assertNull(contract.m_expiry);
        assertNull(contract.m_right);
        assertNull(contract.m_multiplier);
        assertNull(contract.m_exchange);
        assertNull(contract.m_currency);
        assertNull(contract.m_localSymbol);
        assertNull(contract.m_primaryExch);
        assertNull(contract.m_secIdType);
        assertNull(contract.m_secId);
        assertNull(contract.m_underComp);
        assertNotNull(contract.m_comboLegs);
        assertEquals(0, contract.m_comboLegs.size());
    }

    @Test
    void testContractParameterizedConstructor() {
        Vector comboLegs = new Vector();
        comboLegs.add("leg1");
        UnderComp underComp = new UnderComp();
        Contract contract = new Contract(1, "AAPL", "STK", "20221231", 150.0, "C", "100", "NASDAQ", "USD", "AAPL LOC",
                comboLegs, "NASDAQ", true, "CUSIP", "123456");

        assertEquals(1, contract.m_conId);
        assertEquals("AAPL", contract.m_symbol);
        assertEquals("STK", contract.m_secType);
        assertEquals("20221231", contract.m_expiry);
        assertEquals(150.0, contract.m_strike, 0.00001);
        assertEquals("C", contract.m_right);
        assertEquals("100", contract.m_multiplier);
        assertEquals("NASDAQ", contract.m_exchange);
        assertEquals("USD", contract.m_currency);
        assertEquals("AAPL LOC", contract.m_localSymbol);
        assertEquals(comboLegs, contract.m_comboLegs);
        assertEquals("NASDAQ", contract.m_primaryExch);
        assertEquals(true, contract.m_includeExpired);
        assertEquals("CUSIP", contract.m_secIdType);
        assertEquals("123456", contract.m_secId);
    }

    @Test
    void testCloneContract() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("leg1");
        Contract contract = new Contract(1, "AAPL", "STK", "20221231", 150.0, "C", "100", "NASDAQ", "USD", "AAPL LOC",
                comboLegs, "NASDAQ", true, "CUSIP", "123456");

        Contract clonedContract = (Contract) contract.clone();

        assertEquals(contract.m_conId, clonedContract.m_conId);
        assertEquals(contract.m_symbol, clonedContract.m_symbol);
        assertEquals(contract.m_secType, clonedContract.m_secType);
        assertEquals(contract.m_expiry, clonedContract.m_expiry);
        assertEquals(contract.m_strike, clonedContract.m_strike, 0.00001);
        assertEquals(contract.m_right, clonedContract.m_right);
        assertEquals(contract.m_multiplier, clonedContract.m_multiplier);
        assertEquals(contract.m_exchange, clonedContract.m_exchange);
        assertEquals(contract.m_currency, clonedContract.m_currency);
        assertEquals(contract.m_localSymbol, clonedContract.m_localSymbol);
        assertEquals(contract.m_comboLegs, clonedContract.m_comboLegs);
        assertEquals(contract.m_primaryExch, clonedContract.m_primaryExch);
        assertEquals(contract.m_includeExpired, clonedContract.m_includeExpired);
        assertEquals(contract.m_secIdType, clonedContract.m_secIdType);
        assertEquals(contract.m_secId, clonedContract.m_secId);

        // Ensuring m_comboLegs is deeply cloned
        assertNotSame(contract.m_comboLegs, clonedContract.m_comboLegs);

        // Ensuring m_underComp is deeply cloned
        if (contract.m_underComp != null) {
            assertEquals(contract.m_underComp, clonedContract.m_underComp);
        } else {
            assertNull(clonedContract.m_underComp);
        }
    }

    @Test
    void testEquals() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("leg1");
        Contract contract1 = new Contract(1, "AAPL", "STK", "20221231", 150.0, "C", "100", "NASDAQ", "USD", "AAPL LOC",
                comboLegs1, "NASDAQ", true, "CUSIP", "123456");
        
        Vector comboLegs2 = new Vector();
        comboLegs2.add("leg1");
        Contract contract2 = new Contract(1, "AAPL", "STK", "20221231", 150.0, "C", "100", "NASDAQ", "USD", "AAPL LOC",
                comboLegs2, "NASDAQ", true, "CUSIP", "123456");

        assertTrue(contract1.equals(contract2));

        contract2.m_symbol = "GOOG";
        assertFalse(contract1.equals(contract2));
    }
}
```