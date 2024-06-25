
package org.example.run10;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AxisTest {

    @Test
    public void testLookupByNumber() {
        assertEquals("child", Axis.lookup(Axis.CHILD));
        assertEquals("descendant", Axis.lookup(Axis.DESCENDANT));
        assertEquals("parent", Axis.lookup(Axis.PARENT));
        assertEquals("ancestor", Axis.lookup(Axis.ANCESTOR));
        assertEquals("following-sibling", Axis.lookup(Axis.FOLLOWING_SIBLING));
        assertEquals("preceding-sibling", Axis.lookup(Axis.PRECEDING_SIBLING));
        assertEquals("following", Axis.lookup(Axis.FOLLOWING));
        assertEquals("preceding", Axis.lookup(Axis.PRECEDING));
        assertEquals("attribute", Axis.lookup(Axis.ATTRIBUTE));
        assertEquals("namespace", Axis.lookup(Axis.NAMESPACE));
        assertEquals("self", Axis.lookup(Axis.SELF));
        assertEquals("descendant-or-self", Axis.lookup(Axis.DESCENDANT_OR_SELF));
        assertEquals("ancestor-or-self", Axis.lookup(Axis.ANCESTOR_OR_SELF));
        assertNull(Axis.lookup(Axis.INVALID_AXIS));
        assertNull(Axis.lookup(15)); // Test for a number not defined
    }

    @Test
    public void testLookupByName() {
        assertEquals(Axis.CHILD, Axis.lookup("child"));
        assertEquals(Axis.DESCENDANT, Axis.lookup("descendant"));
        assertEquals(Axis.PARENT, Axis.lookup("parent"));
        assertEquals(Axis.ANCESTOR, Axis.lookup("ancestor"));
        assertEquals(Axis.FOLLOWING_SIBLING, Axis.lookup("following-sibling"));
        assertEquals(Axis.PRECEDING_SIBLING, Axis.lookup("preceding-sibling"));
        assertEquals(Axis.FOLLOWING, Axis.lookup("following"));
        assertEquals(Axis.PRECEDING, Axis.lookup("preceding"));
        assertEquals(Axis.ATTRIBUTE, Axis.lookup("attribute"));
        assertEquals(Axis.NAMESPACE, Axis.lookup("namespace"));
        assertEquals(Axis.SELF, Axis.lookup("self"));
        assertEquals(Axis.DESCENDANT_OR_SELF, Axis.lookup("descendant-or-self"));
        assertEquals(Axis.ANCESTOR_OR_SELF, Axis.lookup("ancestor-or-self"));
        assertEquals(Axis.INVALID_AXIS, Axis.lookup("invalid"));
        assertEquals(Axis.INVALID_AXIS, Axis.lookup("")); // Test for an empty string
    }
}
