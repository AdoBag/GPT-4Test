
package org.example.run9;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ContractTest {
    
    @Test
    public void testDefaultConstructor() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
        assertNull(contract.m_symbol);
        assertNull(contract.m_secType);
        assertNull(contract.m_expiry);
        assertEquals(0, contract.m_strike, 0.0001);
        assertNull(contract.m_right);
        assertNull(contract.m_multiplier);
        assertNull(contract.m_exchange);
        assertNull(contract.m_currency);
        assertNull(contract.m_localSymbol);
        assertNull(contract.m_primaryExch);
        assertFalse(contract.m_includeExpired);
        assertNull(contract.m_secIdType);
        assertNull(contract.m_secId);
        assertNull(contract.m_comboLegsDescrip);
        assertNotNull(contract.m_comboLegs);
        assertTrue(contract.m_comboLegs.isEmpty());
        assertNull(contract.m_underComp);
    }

    @Test
    public void testParameterizedConstructor() {
        Vector comboLegs = new Vector();
        comboLegs.add("comboLeg");
        Contract contract = new Contract(1, "symbol", "secType", "expiry", 100.0, "right", "multiplier", "exchange", "currency", "localSymbol", comboLegs, "primaryExch", true, "secIdType", "secId");

        assertEquals(1, contract.m_conId);
        assertEquals("symbol", contract.m_symbol);
        assertEquals("secType", contract.m_secType);
        assertEquals("expiry", contract.m_expiry);
        assertEquals(100.0, contract.m_strike, 0.0001);
        assertEquals("right", contract.m_right);
        assertEquals("multiplier", contract.m_multiplier);
        assertEquals("exchange", contract.m_exchange);
        assertEquals("currency", contract.m_currency);
        assertEquals("localSymbol", contract.m_localSymbol);
        assertEquals("primaryExch", contract.m_primaryExch);
        assertTrue(contract.m_includeExpired);
        assertEquals("secIdType", contract.m_secIdType);
        assertEquals("secId", contract.m_secId);
        assertEquals(comboLegs, contract.m_comboLegs);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("comboLeg");
        Contract contract = new Contract(1, "symbol", "secType", "expiry", 100.0, "right", "multiplier", "exchange", "currency", "localSymbol", comboLegs, "primaryExch", true, "secIdType", "secId");
        Contract copiedContract = (Contract) contract.clone();

        assertEquals(contract, copiedContract);
        assertNotSame(contract, copiedContract);
        assertNotSame(contract.m_comboLegs, copiedContract.m_comboLegs);
    }

    @Test
    public void testEqualsSameObject() {
        Contract contract = new Contract(1, "symbol", "secType", "expiry", 100.0, "right", "multiplier", "exchange", "currency", "localSymbol", new Vector(), "primaryExch", true, "secIdType", "secId");
        assertTrue(contract.equals(contract));
    }

    @Test
    public void testEqualsNull() {
        Contract contract = new Contract(1, "symbol", "secType", "expiry", 100.0, "right", "multiplier", "exchange", "currency", "localSymbol", new Vector(), "primaryExch", true, "secIdType", "secId");
        assertFalse(contract.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Contract contract = new Contract(1, "symbol", "secType", "expiry", 100.0, "right", "multiplier", "exchange", "currency", "localSymbol", new Vector(), "primaryExch", true, "secIdType", "secId");
        assertFalse(contract.equals("Some String"));
    }

    @Test
    public void testEqualsDifferentContracts() {
        Contract contract1 = new Contract(1, "symbol1", "secType1", "expiry1", 100.0, "right1", "multiplier1", "exchange1", "currency1", "localSymbol1", new Vector(), "primaryExch1", true, "secIdType1", "secId1");
        Contract contract2 = new Contract(2, "symbol2", "secType2", "expiry2", 200.0, "right2", "multiplier2", "exchange2", "currency2", "localSymbol2", new Vector(), "primaryExch2", false, "secIdType2", "secId2");

        assertFalse(contract1.equals(contract2));
    }
}
