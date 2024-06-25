
package org.example.run4;

import java.util.Vector;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContractTest {

    @Test
    public void testDefaultConstructor() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
        assertEquals(0.0, contract.m_strike, 0);
        assertFalse(contract.m_includeExpired);
    }

    @Test
    public void testParameterizedConstructor() {
        Vector comboLegs = new Vector();
        Contract contract = new Contract(1, "AAPL", "STK", "202212", 145.0, "C", "100",
                                         "NASDAQ", "USD", "AAPL", comboLegs, "BEST", true, "ISIN", "US0378331005");
        assertEquals(1, contract.m_conId);
        assertEquals("AAPL", contract.m_symbol);
        assertEquals("STK", contract.m_secType);
        assertEquals("202212", contract.m_expiry);
        assertEquals(145.0, contract.m_strike, 0);
        assertEquals("C", contract.m_right);
        assertEquals("100", contract.m_multiplier);
        assertEquals("NASDAQ", contract.m_exchange);
        assertEquals("USD", contract.m_currency);
        assertEquals("AAPL", contract.m_localSymbol);
        assertEquals(comboLegs, contract.m_comboLegs);
        assertEquals("BEST", contract.m_primaryExch);
        assertTrue(contract.m_includeExpired);
        assertEquals("ISIN", contract.m_secIdType);
        assertEquals("US0378331005", contract.m_secId);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("leg1");
        comboLegs.add("leg2");

        Contract original = new Contract(1, "AAPL", "STK", "202212", 145.0, "C", "100",
                                         "NASDAQ", "USD", "AAPL", comboLegs, "BEST", true, "ISIN", "US0378331005");
        Contract cloned = (Contract) original.clone();

        assertEquals(original.m_conId, cloned.m_conId);
        assertEquals(original.m_symbol, cloned.m_symbol);
        assertEquals(original.m_secType, cloned.m_secType);
        assertEquals(original.m_expiry, cloned.m_expiry);
        assertEquals(original.m_strike, cloned.m_strike, 0);
        assertEquals(original.m_right, cloned.m_right);
        assertEquals(original.m_multiplier, cloned.m_multiplier);
        assertEquals(original.m_exchange, cloned.m_exchange);
        assertEquals(original.m_currency, cloned.m_currency);
        assertEquals(original.m_localSymbol, cloned.m_localSymbol);
        assertEquals(original.m_comboLegs, cloned.m_comboLegs);
        assertEquals(original.m_primaryExch, cloned.m_primaryExch);
        assertEquals(original.m_includeExpired, cloned.m_includeExpired);
        assertEquals(original.m_secIdType, cloned.m_secIdType);
        assertEquals(original.m_secId, cloned.m_secId);
        assertEquals(original.m_underComp, cloned.m_underComp);

        // Ensure deep copy of comboLegs
        assertNotSame(original.m_comboLegs, cloned.m_comboLegs);
    }

    @Test
    public void testEquals() {
        Vector comboLegs = new Vector();
        Contract contract1 = new Contract(1, "AAPL", "STK", "202212", 145.0, "C", "100",
                                          "NASDAQ", "USD", "AAPL", comboLegs, "BEST", true, "ISIN", "US0378331005");
        Contract contract2 = new Contract(1, "AAPL", "STK", "202212", 145.0, "C", "100",
                                          "NASDAQ", "USD", "AAPL", comboLegs, "BEST", true, "ISIN", "US0378331005");

        assertTrue(contract1.equals(contract2));
        assertFalse(contract1.equals(null));
        assertFalse(contract1.equals(new Object()));

        contract2.m_symbol = "GOOG";
        assertFalse(contract1.equals(contract2));
    }
}
