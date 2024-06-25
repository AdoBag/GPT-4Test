
package org.example.run3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HtmlEncoderTest {

    @Test
    void testEncodeWithoutSpecialCharacters() {
        String input = "Hello World";
        String expected = "Hello World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithQuotes() {
        String input = "He said, \"Hello!\"";
        String expected = "He said, &quot;Hello!&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithAmpersand() {
        String input = "Fish & Chips";
        String expected = "Fish &amp; Chips";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithLessThan() {
        String input = "2 < 3";
        String expected = "2 &lt; 3";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithGreaterThan() {
        String input = "5 > 3";
        String expected = "5 &gt; 3";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithUnicodeCharacters() {
        String input = "Café";
        String expected = "Caf&#233;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
    
    @Test
    void testEncodeWithMultipleSpecialCharacters() {
        String input = "\"Fish < & > \"";
        String expected = "&quot;Fish &lt; &amp; &gt; &quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeEmptyString() {
        String input = "";
        String expected = "";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
    
    @Test
    void testEncodeOnlySpecialCharacters() {
        String input = "\"<&>\"";
        String expected = "&quot;&lt;&amp;&gt;&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
}
