
package org.example.run8;

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
        assertEquals("", Util.NormalizeString(""));
        assertEquals("test", Util.NormalizeString("test"));
    }

    @Test
    public void testStringCompare() {
        assertTrue(Util.StringCompare(null, null) == 0);
        assertTrue(Util.StringCompare("", "") == 0);
        assertTrue(Util.StringCompare("a", "a") == 0);
        assertTrue(Util.StringCompare("a", "b") < 0);
        assertTrue(Util.StringCompare("b", "a") > 0);
        assertTrue(Util.StringCompare("test", null) > 0);
        assertTrue(Util.StringCompare(null, "test") < 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        assertTrue(Util.StringCompareIgnCase(null, null) == 0);
        assertTrue(Util.StringCompareIgnCase("", "") == 0);
        assertTrue(Util.StringCompareIgnCase("a", "A") == 0);
        assertTrue(Util.StringCompareIgnCase("a", "b") < 0);
        assertTrue(Util.StringCompareIgnCase("b", "a") > 0);
        assertTrue(Util.StringCompareIgnCase("Test", "test") == 0);
        assertTrue(Util.StringCompareIgnCase("test", null) > 0);
        assertTrue(Util.StringCompareIgnCase(null, "test") < 0);
    }

    @Test
    public void testVectorEqualsUnordered() {
        Vector<Integer> vec1 = new Vector<>();
        Vector<Integer> vec2 = new Vector<>();
        
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec1.add(1);
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec2.add(1);
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec1.add(2);
        vec2.add(2);
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec2.clear();
        vec2.add(2);
        vec2.add(1);
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec2.add(3);
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("100", Util.IntMaxString(100));
    }

    @Test
    public void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("100.0", Util.DoubleMaxString(100.0));
    }
}
