```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;

public class UtilTest {

    @Test
    void testStringIsEmpty() {
        assertTrue(Util.StringIsEmpty(null));
        assertTrue(Util.StringIsEmpty(""));
        assertFalse(Util.StringIsEmpty(" "));
        assertFalse(Util.StringIsEmpty("test"));
    }

    @Test
    void testNormalizeString() {
        assertEquals("", Util.NormalizeString(null));
        assertEquals("", Util.NormalizeString(""));
        assertEquals("test", Util.NormalizeString("test"));
    }

    @Test
    void testStringCompare() {
        assertEquals(0, Util.StringCompare(null, null));
        assertEquals(0, Util.StringCompare("", null));
        assertEquals(0, Util.StringCompare(null, ""));
        assertEquals(0, Util.StringCompare("", ""));
        assertTrue(Util.StringCompare("a", "b") < 0);
        assertTrue(Util.StringCompare("b", "a") > 0);
    }

    @Test
    void testStringCompareIgnCase() {
        assertEquals(0, Util.StringCompareIgnCase(null, null));
        assertEquals(0, Util.StringCompareIgnCase("", null));
        assertEquals(0, Util.StringCompareIgnCase(null, ""));
        assertEquals(0, Util.StringCompareIgnCase("", ""));
        assertEquals(0, Util.StringCompareIgnCase("A", "a"));
        assertTrue(Util.StringCompareIgnCase("a", "B") < 0);
        assertTrue(Util.StringCompareIgnCase("B", "a") > 0);
    }
    
    @Test
    void testVectorEqualsUnordered() {
        Vector<Object> vector1 = new Vector<>();
        Vector<Object> vector2 = new Vector<>();

        assertTrue(Util.VectorEqualsUnordered(vector1, vector2));

        vector1.add(1);
        vector2.add(1);
        assertTrue(Util.VectorEqualsUnordered(vector1, vector2));

        vector1.add(2);
        vector2.add(3);
        assertFalse(Util.VectorEqualsUnordered(vector1, vector2));

        vector2.add(2);
        assertTrue(Util.VectorEqualsUnordered(vector1, vector2));
    }

    @Test
    void testIntMaxString() {
        assertEquals("", Util.IntMaxString(Integer.MAX_VALUE));
        assertEquals("100", Util.IntMaxString(100));
    }

    @Test
    void testDoubleMaxString() {
        assertEquals("", Util.DoubleMaxString(Double.MAX_VALUE));
        assertEquals("100.0", Util.DoubleMaxString(100.0));
    }
}
```