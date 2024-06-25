
package org.example.run4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HTMLFilterTest {

    @Test
    void testNullInput() {
        assertNull(HTMLFilter.filter(null));
    }

    @Test
    void testEmptyString() {
        assertEquals("", HTMLFilter.filter(""));
    }

    @Test
    void testNoSpecialCharacters() {
        assertEquals("Hello World", HTMLFilter.filter("Hello World"));
    }

    @Test
    void testLessThanCharacter() {
        assertEquals("&lt;", HTMLFilter.filter("<"));
        assertEquals("Hello &lt; World", HTMLFilter.filter("Hello < World"));
    }

    @Test
    void testGreaterThanCharacter() {
        assertEquals("&gt;", HTMLFilter.filter(">"));
        assertEquals("Hello &gt; World", HTMLFilter.filter("Hello > World"));
    }

    @Test
    void testAmpersandCharacter() {
        assertEquals("&amp;", HTMLFilter.filter("&"));
        assertEquals("Hello &amp; World", HTMLFilter.filter("Hello & World"));
    }

    @Test
    void testDoubleQuoteCharacter() {
        assertEquals("&quot;", HTMLFilter.filter("\""));
        assertEquals("Hello &quot; World", HTMLFilter.filter("Hello \" World"));
    }

    @Test
    void testMultipleSpecialCharacters() {
        String input = "<Hello> & \"World\"";
        String expected = "&lt;Hello&gt; &amp; &quot;World&quot;";
        assertEquals(expected, HTMLFilter.filter(input));
    }

    @Test
    void testNoChangeNeeded() {
        String input = "Hello World!";
        assertEquals(input, HTMLFilter.filter(input));
    }
}
