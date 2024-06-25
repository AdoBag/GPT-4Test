
package org.example.run8;

import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AxisTest {
    
    private Map<Integer, String> axisLookupMap;
    private Map<String, Integer> axisNameMap;
    
    @Before
    public void setUp() {
        axisLookupMap = new HashMap<>();
        axisLookupMap.put(Axis.CHILD, "child");
        axisLookupMap.put(Axis.DESCENDANT, "descendant");
        axisLookupMap.put(Axis.PARENT, "parent");
        axisLookupMap.put(Axis.ANCESTOR, "ancestor");
        axisLookupMap.put(Axis.FOLLOWING_SIBLING, "following-sibling");
        axisLookupMap.put(Axis.PRECEDING_SIBLING, "preceding-sibling");
        axisLookupMap.put(Axis.FOLLOWING, "following");
        axisLookupMap.put(Axis.PRECEDING, "preceding");
        axisLookupMap.put(Axis.ATTRIBUTE, "attribute");
        axisLookupMap.put(Axis.NAMESPACE, "namespace");
        axisLookupMap.put(Axis.SELF, "self");
        axisLookupMap.put(Axis.DESCENDANT_OR_SELF, "descendant-or-self");
        axisLookupMap.put(Axis.ANCESTOR_OR_SELF, "ancestor-or-self");

        axisNameMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : axisLookupMap.entrySet()) {
            axisNameMap.put(entry.getValue(), entry.getKey());
        }
    }

    @Test
    public void testLookupByAxisNumber() {
        for (Map.Entry<Integer, String> entry : axisLookupMap.entrySet()) {
            assertEquals(entry.getValue(), Axis.lookup(entry.getKey()));
        }
        assertNull(Axis.lookup(Axis.INVALID_AXIS));
        assertNull(Axis.lookup(999)); // Arbitrary invalid value
    }

    @Test
    public void testLookupByAxisName() {
        for (Map.Entry<String, Integer> entry : axisNameMap.entrySet()) {
            assertEquals((int)entry.getValue(), Axis.lookup(entry.getKey()));
        }
        assertEquals(Axis.INVALID_AXIS, Axis.lookup("invalid-axis-name"));
    }
}
