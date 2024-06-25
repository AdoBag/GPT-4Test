
package org.example.run5;

import org.junit.jupiter.api.Test;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(0, Util.StringCompare(null, null));
        assertEquals(0, Util.StringCompare("", ""));
        assertTrue(Util.StringCompare("a", "b") < 0);
        assertTrue(Util.StringCompare("b", "a") > 0);
        assertEquals(0, Util.StringCompare("test", "test"));
    }

    @Test
    public void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase(null, null));
        assertEquals(0, Util.StringCompareIgnCase("", ""));
        assertTrue(Util.StringCompareIgnCase("a", "B") < 0);
        assertTrue(Util.StringCompareIgnCase("B", "a") > 0);
        assertEquals(0, Util.StringCompareIgnCase("test", "Test"));
    }

    @Test
    public void testVectorEqualsUnordered() {
        Vector<String> vec1 = new Vector<>();
        Vector<String> vec2 = new Vector<>();
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));

        vec1.add("one");
        vec2.add("one");
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));

        vec1.add("two");
        vec2.add("two");
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));

        vec1.add("three");
        vec2.add("four");
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));

        vec1.clear();
        vec1.add("one");
        vec1.add("two");

        vec2.clear();
        vec2.add("two");
        vec2.add("one");
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("12345", Util.IntMaxString(12345));
    }

    @Test
    public void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("12345.678", Util.DoubleMaxString(12345.678));
    }
}
