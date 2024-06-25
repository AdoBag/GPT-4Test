
package org.example.run9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HtmlEncoderTest {

    @Test
    public void testEncodeWithSimpleString() {
        String input = "Hello, World!";
        String expected = "Hello, World!";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithQuote() {
        String input = "He said \"Hello\"";
        String expected = "He said &quot;Hello&quot;";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithAmpersand() {
        String input = "Fish & Chips";
        String expected = "Fish &amp; Chips";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithLessThan() {
        String input = "5 < 6";
        String expected = "5 &lt; 6";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithGreaterThan() {
        String input = "7 > 6";
        String expected = "7 &gt; 6";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithNonAsciiCharacters() {
        String input = "Café";
        String expected = "Caf&#233;";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithMultipleSpecialCharacters() {
        String input = "\"Fish & Chips\" < Café>";
        String expected = "&quot;Fish &amp; Chips&quot; &lt; Caf&#233;&gt;";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeWithNoSpecialCharacters() {
        String input = "Lorem ipsum dolor sit amet";
        String expected = "Lorem ipsum dolor sit amet";
        String result = HtmlEncoder.encode(input);
        assertEquals(expected, result);
    }
}
