
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class ContractTest {

    @Test
//    public void testDefaultConstructor() {
    public void testDefaultConstructor__fixed() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
//        assertEquals("", contract.m_symbol);
//        assertEquals("", contract.m_secType);
        /** These values are not initialised in the constructor */
        assertNull(contract.m_symbol);
        assertNull(contract.m_secType);


        assertNull(contract.m_expiry);
        assertEquals(0.0, contract.m_strike);
//        assertEquals("", contract.m_right);
//        assertEquals("", contract.m_multiplier);
//        assertEquals("", contract.m_exchange);
//        assertEquals("", contract.m_currency);
//        assertEquals("", contract.m_localSymbol);
//        assertEquals("", contract.m_primaryExch);
        /** These values are not initialised in the constructor */
        assertNull(contract.m_right);
        assertNull(contract.m_multiplier);
        assertNull(contract.m_exchange);
        assertNull(contract.m_currency);
        assertNull(contract.m_localSymbol);
        assertNull(contract.m_primaryExch);

        assertFalse(contract.m_includeExpired);
        assertNull(contract.m_underComp);

        assertEquals(0, contract.m_comboLegs.size());
    }

    @Test
    public void testParameterizedConstructor() {
        Vector comboLegs = new Vector();
        comboLegs.add("Leg1");
        comboLegs.add("Leg2");
        Contract contract = new Contract(1, "AAPL", "STK", "202312", 150.0, "C", "100", "SMART", "USD", "AAPL", comboLegs, "NASDAQ", true, "ISIN", "US0378331005");

        assertEquals(1, contract.m_conId);
        assertEquals("AAPL", contract.m_symbol);
        assertEquals("STK", contract.m_secType);
        assertEquals("202312", contract.m_expiry);
        assertEquals(150.0, contract.m_strike);
        assertEquals("C", contract.m_right);
        assertEquals("100", contract.m_multiplier);
        assertEquals("SMART", contract.m_exchange);
        assertEquals("USD", contract.m_currency);
        assertEquals("AAPL", contract.m_localSymbol);
        assertEquals("NASDAQ", contract.m_primaryExch);
        assertTrue(contract.m_includeExpired);
        assertEquals("ISIN", contract.m_secIdType);
        assertEquals("US0378331005", contract.m_secId);
        assertEquals(2, contract.m_comboLegs.size());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("Leg1");
        comboLegs.add("Leg2");
        Contract original = new Contract(1, "AAPL", "STK", "202312", 150.0, "C", "100", "SMART", "USD", "AAPL", comboLegs, "NASDAQ", true, "ISIN", "US0378331005");
        Contract clone = (Contract) original.clone();

        assertEquals(original, clone);
        assertNotSame(original, clone);
        assertNotSame(original.m_comboLegs, clone.m_comboLegs);
    }

    @Test
    public void testEquals() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("Leg1");
        comboLegs1.add("Leg2");
        Contract contract1 = new Contract(1, "AAPL", "STK", "202312", 150.0, "C", "100", "SMART", "USD", "AAPL", comboLegs1, "NASDAQ", true, "ISIN", "US0378331005");

        Vector comboLegs2 = new Vector();
        comboLegs2.add("Leg1");
        comboLegs2.add("Leg2");
        Contract contract2 = new Contract(1, "AAPL", "STK", "202312", 150.0, "C", "100", "SMART", "USD", "AAPL", comboLegs2, "NASDAQ", true, "ISIN", "US0378331005");

        assertEquals(contract1, contract2);

        contract2.m_symbol = "MSFT";
        assertNotEquals(contract1, contract2);
    }

    @Test
    public void testNotEqualsDifferentObject() {
        Contract contract = new Contract();
        String otherObject = "different";
        assertNotEquals(contract, otherObject);
    }
}
