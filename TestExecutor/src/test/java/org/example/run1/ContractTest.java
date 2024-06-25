
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.dependencies.UnderComp;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ContractTest {

    private Contract contract1;
    private Contract contract2;

    @BeforeEach
    public void setUp() {
        contract1 = new Contract(1, "AAPL", "STK", "20221021", 150.0, "C", "100",
                "SMART", "USD", "AAPL", new Vector(), "NASDAQ", false, "CUSIP", "123456");
        
        contract2 = new Contract(1, "AAPL", "STK", "20221021", 150.0, "C", "100",
                "SMART", "USD", "AAPL", new Vector(), "NASDAQ", false, "CUSIP", "123456");
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Contract clonedContract = (Contract) contract1.clone();
        assertEquals(contract1, clonedContract, "The cloned contract should be equal to the original");
        assertNotSame(contract1, clonedContract, "The cloned contract should not be the same instance as the original");
        assertNotSame(contract1.m_comboLegs, clonedContract.m_comboLegs, "The comboLegs Vector should be a different instance");
    }

    @Test
    public void testEquals() {
        assertEquals(contract1, contract2, "Two identical contracts should be equal");

        contract2.m_conId = 2;
        assertNotEquals(contract1, contract2, "Contracts with different conIds should not be equal");

        contract2.m_conId = contract1.m_conId;
        contract2.m_symbol = "GOOG";
        assertNotEquals(contract1, contract2, "Contracts with different symbols should not be equal");

        contract2.m_symbol = contract1.m_symbol;
        contract2.m_secType = "OPT";
        assertNotEquals(contract1, contract2, "Contracts with different secTypes should not be equal");
    }

    @Test
    public void testComboLegsEquals() {
        Contract contract3 = new Contract(1, "AAPL", "STK", "20221021", 150.0, "C", "100",
                "SMART", "USD", "AAPL", new Vector(), "NASDAQ", false, "CUSIP", "123456");
   
        // Add combo legs and ensure equality is preserved as per implementation
        contract1.m_comboLegs.add("leg1");
        contract3.m_comboLegs.add("leg1");
   
        assertEquals(contract1, contract3, "Contracts with same combo legs should be equal");
   
        contract3.m_comboLegs.add("leg2");
        assertNotEquals(contract1, contract3, "Contracts with different combo legs should not be equal");
    }
}
