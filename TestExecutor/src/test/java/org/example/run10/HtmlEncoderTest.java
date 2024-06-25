
package org.example.run10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HtmlEncoderTest {

    @Test
    public void testEncodeWithNoSpecialCharacters() {
        String input = "HelloWorld";
        String expectedOutput = "HelloWorld";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithSingleSpecialCharacterQuote() {
        String input = "Hello\"World";
        String expectedOutput = "Hello&quot;World";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithSingleSpecialCharacterAmpersand() {
        String input = "Hello&World";
        String expectedOutput = "Hello&amp;World";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithSingleSpecialCharacterLessThan() {
        String input = "Hello<World";
        String expectedOutput = "Hello&lt;World";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithSingleSpecialCharacterGreaterThan() {
        String input = "Hello>World";
        String expectedOutput = "Hello&gt;World";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithMultipleSpecialCharacters() {
        String input = "\"Hello&<World>\"";
        String expectedOutput = "&quot;Hello&amp;&lt;World&gt;&quot;";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithNonAsciiCharacters() {
        String input = "Hello\u00A9World";
        String expectedOutput = "Hello&#169;World";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithEmptyString() {
        String input = "";
        String expectedOutput = "";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithOnlySpecialCharacters() {
        String input = "\"&<>\"";
        String expectedOutput = "&quot;&amp;&lt;&gt;&quot;";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithLongString() {
        String input = "This is a simple test string with a mix of special characters like \"&<> and some non-ASCII: \u00A9\u00AE\u20AC.";
        String expectedOutput = "This is a simple test string with a mix of special characters like &quot;&amp;&lt;&gt; and some non-ASCII: &#169;&#174;&#8364;.";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
