```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HtmlEncoderTest {

    @Test
    void testEncodeWithNoSpecialCharacters() {
        String input = "HelloWorld";
        String expected = "HelloWorld";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithQuote() {
        String input = "Hello\"World";
        String expected = "Hello&quot;World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithAmpersand() {
        String input = "Hello&World";
        String expected = "Hello&amp;World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithLessThan() {
        String input = "Hello<World";
        String expected = "Hello&lt;World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithGreaterThan() {
        String input = "Hello>World";
        String expected = "Hello&gt;World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithMultipleSpecialCharacters() {
        String input = "\"Hello&World<Goodbye>\"";
        String expected = "&quot;Hello&amp;World&lt;Goodbye&gt;&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithUnicodeCharacters() {
        String input = "Hello\u00A1World"; // ¡ - Inverted Exclamation mark
        String expected = "Hello&#161;World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithMixedCharacters() {
        String input = "Hello&World<Goodbye> \"\u00A1\"";
        String expected = "Hello&amp;World&lt;Goodbye&gt; &quot;&#161;&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
}
```