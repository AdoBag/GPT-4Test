
package org.example.run7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HTMLFilterTest {

    @Test
    void testFilterNullMessage() {
        String message = null;
        String expected = null;
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterEmptyMessage() {
        String message = "";
        String expected = "";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterMessageWithNoSpecialChars() {
        String message = "Hello World";
        String expected = "Hello World";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterMessageWithAngleBrackets() {
        String message = "<html>";
        String expected = "&lt;html&gt;";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterMessageWithAmpersand() {
        String message = "rock & roll";
        String expected = "rock &amp; roll";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterMessageWithQuotes() {
        String message = "she said \"hello\"";
        String expected = "she said &quot;hello&quot;";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterMessageWithMultipleSpecialChars() {
        String message = "<b>rock & roll</b> \"awesome\"";
        String expected = "&lt;b&gt;rock &amp; roll&lt;/b&gt; &quot;awesome&quot;";
        assertEquals(expected, HTMLFilter.filter(message));
    }

    @Test
    void testFilterLongString() {
        String message = "a".repeat(10000) + "<>" + "b".repeat(10000);
        String expected = "a".repeat(10000) + "&lt;&gt;" + "b".repeat(10000);
        assertEquals(expected, HTMLFilter.filter(message));
    }
}
