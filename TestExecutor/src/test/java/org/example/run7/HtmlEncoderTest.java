
package org.example.run7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HtmlEncoderTest {

    @Test
    public void testEncodeWithSpecialCharacters() {
        String input = "This is a test & this is a \"quote\" <tag>.";
        String expectedOutput = "This is a test &amp; this is a &quot;quote&quot; &lt;tag&gt;.";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithNoSpecialCharacters() {
        String input = "This is a simple test.";
        String expectedOutput = "This is a simple test.";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithUnicodeCharacters() {
        String input = "This is a test with unicode: ©, ®, €.";
        String expectedOutput = "This is a test with unicode: &#169;, &#174;, &#8364;.";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithMixedCharacters() {
        String input = "Mixing & special <characters> with unicode ™ and plain text.";
        String expectedOutput = "Mixing &amp; special &lt;characters&gt; with unicode &#8482; and plain text.";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeEmptyString() {
        String input = "";
        String expectedOutput = "";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEncodeWithOnlySpecialCharacters() {
        String input = "&\"<>";
        String expectedOutput = "&amp;&quot;&lt;&gt;";
        String actualOutput = HtmlEncoder.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

}
