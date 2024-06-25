
package org.example.run5;

import org.example.dependencies.UnderComp;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ContractTest {

    @Test
    public void testDefaultConstructor() {
        Contract contract = new Contract();
        assertEquals(0, contract.m_conId);
        assertEquals(0.0, contract.m_strike, 0.0);
        assertFalse(contract.m_includeExpired);
    }

    @Test
    public void testParameterizedConstructor() {
        Vector comboLegs = new Vector();
        comboLegs.add("Leg1");
        Contract contract = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs, "SMART", false, "CUSIP", "123456789");

        assertEquals(1, contract.m_conId);
        assertEquals("AAPL", contract.m_symbol);
        assertEquals("STK", contract.m_secType);
        assertEquals("20230119", contract.m_expiry);
        assertEquals(150.0, contract.m_strike, 0.0);
        assertEquals("C", contract.m_right);
        assertEquals("100", contract.m_multiplier);
        assertEquals("NASDAQ", contract.m_exchange);
        assertEquals("USD", contract.m_currency);
        assertEquals("AAPL", contract.m_localSymbol);
        assertEquals(comboLegs, contract.m_comboLegs);
        assertEquals("SMART", contract.m_primaryExch);
        assertFalse(contract.m_includeExpired);
        assertEquals("CUSIP", contract.m_secIdType);
        assertEquals("123456789", contract.m_secId);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Vector comboLegs = new Vector();
        comboLegs.add("Leg1");
        Contract contract = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs, "SMART", false, "CUSIP", "123456789");
        Contract clonedContract = (Contract) contract.clone();

        assertEquals(contract, clonedContract);
        assertNotSame(contract, clonedContract);
        assertNotSame(contract.m_comboLegs, clonedContract.m_comboLegs);
    }

    @Test
    public void testEquals() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("Leg1");
        Contract contract1 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs1, "SMART", false, "CUSIP", "123456789");

        Vector comboLegs2 = new Vector();
        comboLegs2.add("Leg1");
        Contract contract2 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs2, "SMART", false, "CUSIP", "123456789");

        Vector comboLegs3 = new Vector();
        comboLegs3.add("Leg2");
        Contract contract3 = new Contract(2, "GOOG", "STK", "20240119",
                1400.0, "P", "200", "NASDAQ", "USD", "GOOG",
                comboLegs3, "SMART", true, "SEDOL", "987654321");

        assertEquals(contract1, contract2);
        assertNotEquals(contract1, contract3);
        assertNotEquals(contract2, contract3);
    }

    @Test
    public void testNotEqualsDifferentClass() {
        Contract contract1 = new Contract();
        String someObject = "I am not a Contract";
        assertNotEquals(contract1, someObject);
    }

    @Test
    public void testNotEqualsNull() {
        Contract contract1 = new Contract();
        assertNotEquals(contract1, null);
    }

//    @Test
//    public void testUnderCompEquals() {
//        Vector comboLegs1 = new Vector();
//        comboLegs1.add("Leg1");
//        UnderComp uc1 = new UnderComp(1, 50, 10.5);
//        Contract contract1 = new Contract(1, "AAPL", "STK", "20230119",
//                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
//                comboLegs1, "SMART", false, "CUSIP", "123456789");
//        contract1.m_underComp = uc1;
//
//        Vector comboLegs2 = new Vector();
//        comboLegs2.add("Leg1");
//        UnderComp uc2 = new UnderComp(1, 50, 10.5);
//        Contract contract2 = new Contract(1, "AAPL", "STK", "20230119",
//                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
//                comboLegs2, "SMART", false, "CUSIP", "123456789");
//        contract2.m_underComp = uc2;
//
//        assertEquals(contract1, contract2);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM was not informed that UnderComp had only an empty constructor
     */
    @Test
    public void testUnderCompEquals__fixed() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("Leg1");
        UnderComp uc1 = new UnderComp();
        uc1.m_conId = 1;
        uc1.m_delta = 50;
        uc1.m_price = 10.5;
        Contract contract1 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs1, "SMART", false, "CUSIP", "123456789");
        contract1.m_underComp = uc1;
        Vector comboLegs2 = new Vector();
        comboLegs2.add("Leg1");
        UnderComp uc2 = new UnderComp();
        uc2.m_conId = 1;
        uc2.m_delta = 50;
        uc2.m_price = 10.5;
        Contract contract2 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs2, "SMART", false, "CUSIP", "123456789");
        contract2.m_underComp = uc2;
        assertEquals(contract1, contract2);
    }

//    @Test
//    public void testUnderCompNotEquals() {
//        Vector comboLegs1 = new Vector();
//        comboLegs1.add("Leg1");
//        UnderComp uc1 = new UnderComp(1, 50, 10.5);
//        Contract contract1 = new Contract(1, "AAPL", "STK", "20230119",
//                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
//                comboLegs1, "SMART", false, "CUSIP", "123456789");
//        contract1.m_underComp = uc1;
//
//        Vector comboLegs2 = new Vector();
//        comboLegs2.add("Leg1");
//        UnderComp uc2 = new UnderComp(2, 60, 15.0);
//        Contract contract2 = new Contract(1, "AAPL", "STK", "20230119",
//                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
//                comboLegs2, "SMART", false, "CUSIP", "123456789");
//        contract2.m_underComp = uc2;
//
//        assertNotEquals(contract1, contract2);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM was not informed that UnderComp had only an empty constructor
     */
    @Test
    public void testUnderCompNotEquals__fixed() {
        Vector comboLegs1 = new Vector();
        comboLegs1.add("Leg1");
        UnderComp uc1 = new UnderComp();
        uc1.m_conId = 1;
        uc1.m_delta = 50;
        uc1.m_price = 10.5;
        Contract contract1 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs1, "SMART", false, "CUSIP", "123456789");
        contract1.m_underComp = uc1;
        Vector comboLegs2 = new Vector();
        comboLegs2.add("Leg1");
        UnderComp uc2 = new UnderComp();
        uc2.m_conId = 2;
        uc2.m_delta = 60;
        uc2.m_price = 15.0;
        Contract contract2 = new Contract(1, "AAPL", "STK", "20230119",
                150.0, "C", "100", "NASDAQ", "USD", "AAPL",
                comboLegs2, "SMART", false, "CUSIP", "123456789");
        contract2.m_underComp = uc2;
        assertNotEquals(contract1, contract2);
    }
}
