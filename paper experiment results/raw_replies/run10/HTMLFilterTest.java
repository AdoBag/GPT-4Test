```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HTMLFilterTest {

    @Test
    public void testFilterNull() {
        assertNull(HTMLFilter.filter(null), "Filtering null should return null");
    }

    @Test
    public void testFilterEmptyString() {
        assertEquals("", HTMLFilter.filter(""), "Filtering an empty string should return an empty string");
    }

    @Test
    public void testFilterStringWithoutSpecialCharacters() {
        String input = "Hello World";
        assertEquals(input, HTMLFilter.filter(input), "Filtering a string without special characters should return the same string");
    }

    @Test
    public void testFilterStringWithLessThan() {
        assertEquals("&lt;", HTMLFilter.filter("<"), "Filtering '<' should return '&lt;'");
    }

    @Test
    public void testFilterStringWithGreaterThan() {
        assertEquals("&gt;", HTMLFilter.filter(">"), "Filtering '>' should return '&gt;'");
    }

    @Test
    public void testFilterStringWithAmpersand() {
        assertEquals("&amp;", HTMLFilter.filter("&"), "Filtering '&' should return '&amp;'");
    }

    @Test
    public void testFilterStringWithQuotation() {
        assertEquals("&quot;", HTMLFilter.filter("\""), "Filtering '\"' should return '&quot;'");
    }

    @Test
    public void testFilterComplexString() {
        String input = "Hello <script>alert('test');</script>";
        String expectedOutput = "Hello &lt;script&gt;alert('test');&lt;/script&gt;";
        assertEquals(expectedOutput, HTMLFilter.filter(input), "Filtering a complex string with special characters should return the expected HTML encoded string");
    }

}
```