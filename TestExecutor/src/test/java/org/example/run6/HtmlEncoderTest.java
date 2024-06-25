
package org.example.run6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HtmlEncoderTest {

    @Test
    void testEncodeNoSpecialCharacters() {
        String input = "HelloWorld";
        String expected = "HelloWorld";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithDoubleQuote() {
        String input = "Hello \"World\"";
        String expected = "Hello &quot;World&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithAmpersand() {
        String input = "Hello & World";
        String expected = "Hello &amp; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithLessThan() {
        String input = "Hello < World";
        String expected = "Hello &lt; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithGreaterThan() {
        String input = "Hello > World";
        String expected = "Hello &gt; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithMixedSpecialCharacters() {
        String input = "Hello <World> & \"Everyone\"";
        String expected = "Hello &lt;World&gt; &amp; &quot;Everyone&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithNonAsciiCharacters() {
        String input = "Hello ñ World";
        String expected = "Hello &#241; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithMultipleSpecialCharacters() {
        String input = "<>&\"";
        String expected = "&lt;&gt;&amp;&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithEmptyString() {
        String input = "";
        String expected = "";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithNumericCharacters() {
        String input = "12345";
        String expected = "12345";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithHtmlEntityInMiddle() {
        String input = "Hello &amp; World";
        String expected = "Hello &amp;amp; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
}
