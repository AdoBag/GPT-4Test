
package org.example.run1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Vector;

public class UtilTest {

    @Test
    public void testStringIsEmpty() {
        assertTrue(Util.StringIsEmpty(null));
        assertTrue(Util.StringIsEmpty(""));
        assertFalse(Util.StringIsEmpty("test"));
    }

    @Test
    public void testNormalizeString() {
        assertEquals("", Util.NormalizeString(null));
        assertEquals("test", Util.NormalizeString("test"));
    }

    @Test
    public void testStringCompare() {
        assertEquals(0, Util.StringCompare("test", "test"));
        assertTrue(Util.StringCompare("apple", "banana") < 0);
        assertTrue(Util.StringCompare("banana", "apple") > 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase("TEST", "test"));
        assertTrue(Util.StringCompareIgnCase("apple", "Banana") < 0);
        assertTrue(Util.StringCompareIgnCase("Banana", "apple") > 0);
    }

    @Test
    public void testVectorEqualsUnordered() {
        Vector<Object> vector1 = new Vector<>();
        Vector<Object> vector2 = new Vector<>();
        assertTrue(Util.VectorEqualsUnordered(vector1, vector2));

        vector1.add("a");
        vector1.add("b");
        vector2.add("b");
        vector2.add("a");
        assertTrue(Util.VectorEqualsUnordered(vector1, vector2));

        vector2.add("c");
        assertFalse(Util.VectorEqualsUnordered(vector1, vector2));

        assertFalse(Util.VectorEqualsUnordered(vector1, null));
        assertFalse(Util.VectorEqualsUnordered(null, vector2));
        assertTrue(Util.VectorEqualsUnordered(null, null));
        
        Vector<Object> vector3 = new Vector<>();
        vector3.add("a");
        vector3.add("b");
        vector3.add("a");
        vector3.add("b");
        Vector<Object> vector4 = new Vector<>();
        vector4.add("a");
        vector4.add("a");
        vector4.add("b");
        vector4.add("b");
        assertTrue(Util.VectorEqualsUnordered(vector3, vector4));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("0", Util.IntMaxString(0));
        assertEquals("-1", Util.IntMaxString(-1));
        assertEquals("2147483646", Util.IntMaxString(Integer.MAX_VALUE - 1));
    }

    @Test
//    public void testDoubleMaxString() {
    public void testDoubleMaxString__fixed() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("0.0", Util.DoubleMaxString(0.0));
        assertEquals("-1.0", Util.DoubleMaxString(-1.0));
//        assertEquals("" + (Double.MAX_VALUE - 1), Util.DoubleMaxString(Double.MAX_VALUE - 1));
        /** Doubles are not precise enough for max value - 1 to be distinguishable */
        double smallerThanMax = Math.nextAfter(Double.MAX_VALUE, Double.NEGATIVE_INFINITY);
        assertEquals("" + (smallerThanMax), Util.DoubleMaxString(smallerThanMax));
    }
}
