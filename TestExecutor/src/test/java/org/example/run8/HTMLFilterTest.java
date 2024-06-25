
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HTMLFilterTest {

    @Test
    public void testFilterNullInput() {
        assertNull(HTMLFilter.filter(null));
    }

    @Test
    public void testFilterEmptyString() {
        assertEquals("", HTMLFilter.filter(""));
    }

    @Test
    public void testFilterStringWithoutSpecialCharacters() {
        String input = "Hello World!";
        assertEquals("Hello World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithLessThan() {
        String input = "Hello < World!";
        assertEquals("Hello &lt; World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithGreaterThan() {
        String input = "Hello > World!";
        assertEquals("Hello &gt; World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithAmpersand() {
        String input = "Hello & World!";
        assertEquals("Hello &amp; World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithDoubleQuote() {
        String input = "Hello \" World!";
        assertEquals("Hello &quot; World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithMultipleSpecialCharacters() {
        String input = "Hello < & > \" World!";
        assertEquals("Hello &lt; &amp; &gt; &quot; World!", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithOnlySpecialCharacters() {
        String input = "<>&\"";
        assertEquals("&lt;&gt;&amp;&quot;", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithConsecutiveSpecialCharacters() {
        String input = "<<<";
        assertEquals("&lt;&lt;&lt;", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithLeadingSpecialCharacters() {
        String input = "<Hello";
        assertEquals("&lt;Hello", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithTrailingSpecialCharacters() {
        String input = "World>";
        assertEquals("World&gt;", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterLongString() {
        String input = "Long string with <, >, & and \" characters.";
        assertEquals("Long string with &lt;, &gt;, &amp; and &quot; characters.", HTMLFilter.filter(input));
    }

    @Test
    public void testFilterStringWithNoSpecialCharacters() {
        String input = "Just a normal string.";
        assertEquals("Just a normal string.", HTMLFilter.filter(input));
    }

}
