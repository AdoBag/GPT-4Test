
package org.example.run1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class HTMLFilterTest {

    @Test
    public void testNullInput() {
        assertNull(HTMLFilter.filter(null));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", HTMLFilter.filter(""));
    }

    @Test
    public void testStringWithoutSpecialCharacters() {
        assertEquals("Hello, World!", HTMLFilter.filter("Hello, World!"));
    }

    @Test
    public void testStringWithLessThan() {
        assertEquals("Less than &lt;", HTMLFilter.filter("Less than <"));
    }

    @Test
    public void testStringWithGreaterThan() {
        assertEquals("Greater than &gt;", HTMLFilter.filter("Greater than >"));
    }

    @Test
    public void testStringWithAmpersand() {
        assertEquals("Ampersand &amp;", HTMLFilter.filter("Ampersand &"));
    }

    @Test
    public void testStringWithDoubleQuote() {
        assertEquals("Double quote &quot;", HTMLFilter.filter("Double quote \""));
    }

    @Test
    public void testStringWithMultipleSpecialCharacters() {
        assertEquals("&lt;Hello &amp; &quot;World&quot;&gt;", HTMLFilter.filter("<Hello & \"World\">"));
    }

    @Test
    public void testStringWithSpecialCharactersAtBeginning() {
        assertEquals("&lt;Start", HTMLFilter.filter("<Start"));
    }

    @Test
    public void testStringWithSpecialCharactersAtEnd() {
        assertEquals("End&gt;", HTMLFilter.filter("End>"));
    }

    @Test
    public void testStringWithAllSpecialCharacters() {
        assertEquals("&lt;&gt;&amp;&quot;", HTMLFilter.filter("<>&\""));
    }
}
