
package org.example.run2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlEncoderTest {

    @Test
    public void testEncode_NoSpecialCharacters() {
        String input = "HelloWorld";
        String expected = "HelloWorld";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithDoubleQuote() {
        String input = "Hello \"World\"";
        String expected = "Hello &quot;World&quot;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithAmpersand() {
        String input = "Hello & World";
        String expected = "Hello &amp; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithLessThan() {
        String input = "Hello < World";
        String expected = "Hello &lt; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithGreaterThan() {
        String input = "Hello > World";
        String expected = "Hello &gt; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEncode_WithSpecialCharacters() {
        String input = "Hello & < \" > World";
        String expected = "Hello &amp; &lt; &quot; &gt; World";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_WithNon7BitCharacters() {
        String input = "Hello © World";
        String expected = "Hello &#169; World";
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
    public void testEncode_OnlySpecialCharacters() {
        String input = "&<>";
        String expected = "&amp;&lt;&gt;";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode_MixedContent() {
        String input = "Normal text & <special characters> ©2023";
        String expected = "Normal text &amp; &lt;special characters&gt; &#169;2023";
        String actual = HtmlEncoder.encode(input);
        assertEquals(expected, actual);
    }
}
