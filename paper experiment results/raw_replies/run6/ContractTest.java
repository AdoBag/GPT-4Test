```java
package org.example.run1;

import org.example.dependencies.UnderComp;
import org.example.dependencies.Util; // Assuming Util class is placed in org.example.dependencies

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class ContractTest {

    private Contract contract1;
    private Contract contract2;
    private Vector comboLegs1;
    private Vector comboLegs2;
    private UnderComp underComp1;
    private UnderComp underComp2;

    @BeforeEach
    public void setUp() {
        comboLegs1 = new Vector();
        comboLegs2 = new Vector();
        underComp1 = new UnderComp();
        underComp2 = new UnderComp();

        contract1 = new Contract(1, "AAPL", "STK", "202212", 150.0, "C", "100", 
                                 "SMART", "USD", "AAPL", comboLegs1, "NASDAQ", 
                                 false, "ISIN", "US0378331005");
        contract1.m_underComp = underComp1;

        contract2 = new Contract(1, "AAPL", "STK", "202212", 150.0, "C", "100", 
                                 "SMART", "USD", "AAPL", comboLegs2, "NASDAQ", 
                                 false, "ISIN", "US0378331005");
        contract2.m_underComp = underComp2;
    }

    @Test
    public void testEquals_sameObject() {
        assertTrue(contract1.equals(contract1));
    }

    @Test
    public void testEquals_differentType() {
        assertFalse(contract1.equals("Not a Contract"));
    }

    @Test
    public void testEquals_nullObject() {
        assertFalse(contract1.equals(null));
    }

    @Test
    public void testEquals_identicalContracts() {
        assertTrue(contract1.equals(contract2));
    }

    @Test
    public void testEquals_differentConId() {
        Contract contract3 = new Contract(2, "AAPL", "STK", "202212", 150.0, "C", "100", 
                                          "SMART", "USD", "AAPL", comboLegs1, "NASDAQ", 
                                          false, "ISIN", "US0378331005");
        assertFalse(contract1.equals(contract3));
    }

    @Test
    public void testEquals_differentSymbol() {
        Contract contract3 = new Contract(1, "MSFT", "STK", "202212", 150.0, "C", "100", 
                                          "SMART", "USD", "MSFT", comboLegs1, "NASDAQ", 
                                          false, "ISIN", "US0378331005");
        assertFalse(contract1.equals(contract3));
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Contract clonedContract = (Contract) contract1.clone();
        
        // Ensure the cloned contract is not the same instance
        assertNotSame(contract1, clonedContract);
        
        // Ensure the cloned contract is equal to the original
        assertEquals(contract1, clonedContract);
        
        // Ensure comboLegs vector is a deep copy
        assertNotSame(contract1.m_comboLegs, clonedContract.m_comboLegs);
    }

    // Further tests can be created to cover all edge cases and specific fields...
}
```

This test suite uses JUnit 5 to test the `Contract` class, ensuring that the `equals` and `clone` methods work correctly. Ensure to have JUnit 5 in your project's dependencies to run these tests.