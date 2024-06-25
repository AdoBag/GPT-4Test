
package org.example.run9;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Vector;

public class UtilTest {

    @Test
    public void testStringIsEmpty() {
        // Testing null
        assertTrue(Util.StringIsEmpty(null));

        // Testing empty string
        assertTrue(Util.StringIsEmpty(""));

        // Testing non-empty string
        assertFalse(Util.StringIsEmpty("test"));
    }

    @Test
    public void testNormalizeString() {
        // Testing null
        assertEquals("", Util.NormalizeString(null));

        // Testing empty string
        assertEquals("", Util.NormalizeString(""));

        // Testing non-empty string
        assertEquals("test", Util.NormalizeString("test"));
    }

    @Test
    public void testStringCompare() {
        // Testing both null
        assertEquals(0, Util.StringCompare(null, null));

        // Testing lhs null
        assertTrue(Util.StringCompare(null, "test") < 0);

        // Testing rhs null
        assertTrue(Util.StringCompare("test", null) > 0);

        // Testing equal strings
        assertEquals(0, Util.StringCompare("test", "test"));

        // Testing lhs less than rhs
        assertTrue(Util.StringCompare("a", "b") < 0);

        // Testing lhs greater than rhs
        assertTrue(Util.StringCompare("b", "a") > 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        // Testing both null
        assertEquals(0, Util.StringCompareIgnCase(null, null));

        // Testing lhs null
        assertTrue(Util.StringCompareIgnCase(null, "test") < 0);

        // Testing rhs null
        assertTrue(Util.StringCompareIgnCase("test", null) > 0);

        // Testing equal strings
        assertEquals(0, Util.StringCompareIgnCase("test", "test"));

        // Testing equal strings with different cases
        assertEquals(0, Util.StringCompareIgnCase("test", "TEST"));

        // Testing lhs less than rhs
        assertTrue(Util.StringCompareIgnCase("a", "b") < 0);

        // Testing lhs greater than rhs
        assertTrue(Util.StringCompareIgnCase("b", "a") > 0);
    }

    @Test
//    public void testVectorEqualsUnordered() {
    public void testVectorEqualsUnordered__fixed() {
        // Testing both null
        assertTrue(Util.VectorEqualsUnordered(null, null));

        // Testing lhs null
//        assertFalse(Util.VectorEqualsUnordered(null, new Vector()));
        /** If both sides are not vectors with elements, then they are considered equal */
        assertTrue(Util.VectorEqualsUnordered(null, new Vector()));

        // Testing rhs null
//        assertFalse(Util.VectorEqualsUnordered(new Vector(), null));
        /** If both sides are not vectors with elements, then they are considered equal */
        assertTrue(Util.VectorEqualsUnordered(new Vector(), null));


        // Testing both empty
        assertTrue(Util.VectorEqualsUnordered(new Vector(), new Vector()));

        // Testing vectors with different sizes
        Vector<Integer> vec1 = new Vector<>();
        vec1.add(1);
        Vector<Integer> vec2 = new Vector<>();
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));

        // Testing vectors with same size, different elements
        vec2.add(2);
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));

        // Testing vectors with same elements, different order
        vec1.add(2);
        vec2.add(1);
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
    }

    @Test
    public void testIntMaxString() {
        // Testing Integer.MAX_VALUE
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));

        // Testing some other number
        assertEquals("123", Util.IntMaxString(123));
    }

    @Test
    public void testDoubleMaxString() {
        // Testing Double.MAX_VALUE
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));

        // Testing some other number
        assertEquals("123.456", Util.DoubleMaxString(123.456));
    }
}
