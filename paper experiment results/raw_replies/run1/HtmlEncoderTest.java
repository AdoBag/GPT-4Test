```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HtmlEncoderTest {

    @Test
    public void testEncode_NoSpecialChars() {
        String input = "Hello World";
        String expected = "Hello World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithQuotes() {
        String input = "She said \"hello\"";
        String expected = "She said &quot;hello&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithAmpersand() {
        String input = "AT&T";
        String expected = "AT&amp;T";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithLessThan() {
        String input = "5 < 10";
        String expected = "5 &lt; 10";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithGreaterThan() {
        String input = "10 > 5";
        String expected = "10 &gt; 5";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithNonASCII() {
        String input = "Café";
        String expected = "Caf&#233;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithMultipleSpecialChars() {
        String input = "5 > 3 & 2 < 7";
        String expected = "5 &gt; 3 &amp; 2 &lt; 7";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_EmptyString() {
        String input = "";
        String expected = "";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_AllSpecialChars() {
        String input = "\"&<>";
        String expected = "&quot;&amp;&lt;&gt;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
}
```