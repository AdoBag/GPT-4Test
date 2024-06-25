```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class UtilTest {

    @Test
    public void testStringIsEmpty_withNull() {
        assertTrue(Util.StringIsEmpty(null));
    }

    @Test
    public void testStringIsEmpty_withEmptyString() {
        assertTrue(Util.StringIsEmpty(""));
    }

    @Test
    public void testStringIsEmpty_withNonEmptyString() {
        assertFalse(Util.StringIsEmpty("test"));
    }

    @Test
    public void testNormalizeString_withNull() {
        assertEquals("", Util.NormalizeString(null));
    }

    @Test
    public void testNormalizeString_withNonNull() {
        assertEquals("test", Util.NormalizeString("test"));
    }

    @Test
    public void testStringCompare_withBothNull() {
        assertEquals(0, Util.StringCompare(null, null));
    }

    @Test
    public void testStringCompare_withFirstNull() {
        assertTrue(Util.StringCompare(null, "test") < 0);
    }

    @Test
    public void testStringCompare_withSecondNull() {
        assertTrue(Util.StringCompare("test", null) > 0);
    }

    @Test
    public void testStringCompare_withDifferentStrings() {
        assertTrue(Util.StringCompare("a", "b") < 0);
    }

    @Test
    public void testStringCompare_withSameStrings() {
        assertEquals(0, Util.StringCompare("test", "test"));
    }

    @Test
    public void testStringCompareIgnCase_withBothNull() {
        assertEquals(0, Util.StringCompareIgnCase(null, null));
    }

    @Test
    public void testStringCompareIgnCase_withFirstNull() {
        assertTrue(Util.StringCompareIgnCase(null, "test") < 0);
    }

    @Test
    public void testStringCompareIgnCase_withSecondNull() {
        assertTrue(Util.StringCompareIgnCase("test", null) > 0);
    }

    @Test
    public void testStringCompareIgnCase_withDifferentStrings() {
        assertTrue(Util.StringCompareIgnCase("a", "B") < 0);
    }

    @Test
    public void testStringCompareIgnCase_withSameStringsDifferentCase() {
        assertEquals(0, Util.StringCompareIgnCase("Test", "test"));
    }

    @Test
    public void testVectorEqualsUnordered_withBothNull() {
        assertTrue(Util.VectorEqualsUnordered(null, null));
    }

    @Test
    public void testVectorEqualsUnordered_withFirstNull() {
        Vector<String> rhs = new Vector<>();
        rhs.add("test");
        assertFalse(Util.VectorEqualsUnordered(null, rhs));
    }

    @Test
    public void testVectorEqualsUnordered_withSecondNull() {
        Vector<String> lhs = new Vector<>();
        lhs.add("test");
        assertFalse(Util.VectorEqualsUnordered(lhs, null));
    }

    @Test
    public void testVectorEqualsUnordered_withDifferentSize() {
        Vector<String> lhs = new Vector<>();
        lhs.add("test1");
        Vector<String> rhs = new Vector<>();
        rhs.add("test1");
        rhs.add("test2");
        assertFalse(Util.VectorEqualsUnordered(lhs, rhs));
    }

    @Test
    public void testVectorEqualsUnordered_withSameElements() {
        Vector<String> lhs = new Vector<>();
        lhs.add("test1");
        lhs.add("test2");
        Vector<String> rhs = new Vector<>();
        rhs.add("test2");
        rhs.add("test1");
        assertTrue(Util.VectorEqualsUnordered(lhs, rhs));
    }

    @Test
    public void testVectorEqualsUnordered_withDifferentElements() {
        Vector<String> lhs = new Vector<>();
        lhs.add("test1");
        lhs.add("test3");
        Vector<String> rhs = new Vector<>();
        rhs.add("test2");
        rhs.add("test1");
        assertFalse(Util.VectorEqualsUnordered(lhs, rhs));
    }

    @Test
    public void testIntMaxString_maxValue() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
    }

    @Test
    public void testIntMaxString_nonMaxValue() {
        assertEquals("123", Util.IntMaxString(123));
    }

    @Test
    public void testDoubleMaxString_maxValue() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
    }

    @Test
    public void testDoubleMaxString_nonMaxValue() {
        assertEquals("123.45", Util.DoubleMaxString(123.45));
    }
}
```