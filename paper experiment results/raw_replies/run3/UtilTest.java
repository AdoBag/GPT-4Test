```java
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
        assertEquals(0, Util.StringCompare(null, null));
        assertEquals(-1, Util.StringCompare(null, "test"));
        assertEquals(1, Util.StringCompare("test", null));
        assertEquals(0, Util.StringCompare("test", "test"));
        assertTrue(Util.StringCompare("abc", "xyz") < 0);
        assertTrue(Util.StringCompare("xyz", "abc") > 0);
    }

    @Test
    public void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase(null, null));
        assertEquals(-1, Util.StringCompareIgnCase(null, "Test"));
        assertEquals(1, Util.StringCompareIgnCase("Test", null));
        assertEquals(0, Util.StringCompareIgnCase("test", "TEST"));
        assertTrue(Util.StringCompareIgnCase("abc", "XYZ") < 0);
        assertTrue(Util.StringCompareIgnCase("XYZ", "abc") > 0);
    }

    @Test
    public void testVectorEqualsUnordered() {
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>();
        assertTrue(Util.VectorEqualsUnordered(v1, v1));
        assertTrue(Util.VectorEqualsUnordered(v1, v2));
        
        v1.add(1);
        v1.add(2);
        v2.add(2);
        v2.add(1);
        assertTrue(Util.VectorEqualsUnordered(v1, v2));
        
        v2.add(3);
        assertFalse(Util.VectorEqualsUnordered(v1, v2));

        v1.add(3);
        assertTrue(Util.VectorEqualsUnordered(v1, v2));

        v2.remove(new Integer(2));
        assertFalse(Util.VectorEqualsUnordered(v1, v2));
    }

    @Test
    public void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("123", Util.IntMaxString(123));
    }

    @Test
    public void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("123.45", Util.DoubleMaxString(123.45));
    }
}
```
