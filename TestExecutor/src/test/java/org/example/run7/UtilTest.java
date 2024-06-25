
package org.example.run7;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testStringIsEmpty() {
        assertTrue(Util.StringIsEmpty(null));
        assertTrue(Util.StringIsEmpty(""));
        assertFalse(Util.StringIsEmpty("Hello"));
    }

    @Test
    void testNormalizeString() {
        assertEquals("", Util.NormalizeString(null));
        assertEquals("Hello", Util.NormalizeString("Hello"));
    }

    @Test
    void testStringCompare() {
        assertEquals(0, Util.StringCompare("Hello", "Hello"));
        assertTrue(Util.StringCompare("abc", "bcd") < 0);
        assertTrue(Util.StringCompare("xyz", "abc") > 0);
        assertEquals(0, Util.StringCompare(null, ""));
        assertTrue(Util.StringCompare("Hello", null) > 0);
    }

    @Test
    void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase("Hello", "hello"));
        assertTrue(Util.StringCompareIgnCase("abc", "BCD") < 0);
        assertTrue(Util.StringCompareIgnCase("XYZ", "abc") > 0);
        assertEquals(0, Util.StringCompareIgnCase(null, ""));
        assertTrue(Util.StringCompareIgnCase("Hello", null) > 0);
    }

    @Test
    void testVectorEqualsUnordered() {
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>();
        assertTrue(Util.VectorEqualsUnordered(v1, v2));

        v1.add(1);
        v1.add(2);
        v1.add(3);

        v2.add(3);
        v2.add(2);
        v2.add(1);

        assertTrue(Util.VectorEqualsUnordered(v1, v2));

        v2.remove(0);
        v2.add(4);

        assertFalse(Util.VectorEqualsUnordered(v1, v2));

        assertFalse(Util.VectorEqualsUnordered(v1, null));
        assertFalse(Util.VectorEqualsUnordered(null, v2));
        assertTrue(Util.VectorEqualsUnordered(null, null));
    }

    @Test
    void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("123", Util.IntMaxString(123));
        assertEquals("-123", Util.IntMaxString(-123));
    }

    @Test
    void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("123.456", Util.DoubleMaxString(123.456));
        assertEquals("-123.456", Util.DoubleMaxString(-123.456));
    }
}
