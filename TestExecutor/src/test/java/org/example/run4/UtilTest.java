
package org.example.run4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class UtilTest {

    @Test
    public void testStringIsEmpty() {
        assertTrue(Util.StringIsEmpty(null));
        assertTrue(Util.StringIsEmpty(""));
        assertFalse(Util.StringIsEmpty("abc"));
    }

    @Test
    public void testNormalizeString() {
        assertEquals("", Util.NormalizeString(null));
        assertEquals("abc", Util.NormalizeString("abc"));
    }

    @Test
    public void testStringCompare() {
        assertEquals(0, Util.StringCompare("abc", "abc"));
        assertTrue(Util.StringCompare("abc", "abd") < 0);
        assertTrue(Util.StringCompare("abd", "abc") > 0);
        assertEquals(0, Util.StringCompare(null, ""));
        assertTrue(Util.StringCompare(null, "abc") < 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase("abc", "ABC"));
        assertTrue(Util.StringCompareIgnCase("abc", "ABd") < 0);
        assertTrue(Util.StringCompareIgnCase("ABd", "abc") > 0);
        assertEquals(0, Util.StringCompareIgnCase(null, ""));
        assertTrue(Util.StringCompareIgnCase(null, "ABC") < 0);
    }

    @Test
    public void testVectorEqualsUnordered() {
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>();
        v1.add(1); v1.add(2); v1.add(3);
        v2.add(3); v2.add(1); v2.add(2);
        assertTrue(Util.VectorEqualsUnordered(v1, v2));

        v1.clear(); v2.clear();
        v1.add(1); v1.add(2); v2.add(1);
        assertFalse(Util.VectorEqualsUnordered(v1, v2));

        v1.clear(); v2.clear();
        v1.add(1); v1.add(2); v2.add(2); v2.add(3);
        assertFalse(Util.VectorEqualsUnordered(v1, v2));
        
        assertTrue(Util.VectorEqualsUnordered(null, null));
        assertFalse(Util.VectorEqualsUnordered(null, v1));
        assertFalse(Util.VectorEqualsUnordered(v1, null));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("123", Util.IntMaxString(123));
    }

    @Test
    public void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("123.456", Util.DoubleMaxString(123.456));
    }
}
