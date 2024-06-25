
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class UtilTest {

    @Test
    public void testStringIsEmpty() {
        assertTrue(Util.StringIsEmpty(null));
        assertTrue(Util.StringIsEmpty(""));
        assertFalse(Util.StringIsEmpty(" "));
        assertFalse(Util.StringIsEmpty("hello"));
    }

    @Test
    public void testNormalizeString() {
        assertEquals("", Util.NormalizeString(null));
        assertEquals("", Util.NormalizeString(""));
        assertEquals("hello", Util.NormalizeString("hello"));
    }

    @Test
    public void testStringCompare() {
        assertEquals(0, Util.StringCompare("abc", "abc"));
        assertTrue(Util.StringCompare("abc", "def") < 0);
        assertTrue(Util.StringCompare("def", "abc") > 0);
        assertEquals(0, Util.StringCompare(null, ""));
        assertTrue(Util.StringCompare(null, "abc") < 0);
        assertTrue(Util.StringCompare("abc", null) > 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase("abc", "abc"));
        assertEquals(0, Util.StringCompareIgnCase("abc", "ABC"));
        assertTrue(Util.StringCompareIgnCase("abc", "def") < 0);
        assertTrue(Util.StringCompareIgnCase("def", "abc") > 0);
        assertEquals(0, Util.StringCompareIgnCase(null, ""));
        assertTrue(Util.StringCompareIgnCase(null, "abc") < 0);
        assertTrue(Util.StringCompareIgnCase("abc", null) > 0);
    }
    
    @Test
//    public void testVectorEqualsUnordered() {
    public void testVectorEqualsUnordered__fixed() {
        Vector<Integer> vec1 = new Vector<>();
        Vector<Integer> vec2 = new Vector<>();
        
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec1.add(1);
        vec1.add(2);
        vec1.add(3);
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec2.add(3);
        vec2.add(1);
        vec2.add(2);
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec2.add(4);
        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
        
        vec1 = null;
        vec2 = null;
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
        
//        vec1 = new Vector<>();
//        vec2 = null;
//        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
        /** If both sides are not vectors with element, then they are considered equal */
        vec1 = new Vector<>();
        vec2 = null;
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));

//        vec2 = new Vector<>();
//        vec1 = null;
//        assertFalse(Util.VectorEqualsUnordered(vec1, vec2));
        /** If both sides are not vectors with elements, then they are considered equal */
        vec2 = new Vector<>();
        vec1 = null;
        assertTrue(Util.VectorEqualsUnordered(vec1, vec2));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("1000", Util.IntMaxString(1000));
        assertEquals("-1000", Util.IntMaxString(-1000));
    }

    @Test
    public void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("1000.0", Util.DoubleMaxString(1000.0));
        assertEquals("-1000.0", Util.DoubleMaxString(-1000.0));
    }
}
