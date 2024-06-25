```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HtmlEncoderTest {

    @Test
    void testSimpleString() {
        String input = "Hello World";
        String expectedOutput = "Hello World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithQuotes() {
        String input = "Hello \"World\"";
        String expectedOutput = "Hello &quot;World&quot;";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithAmpersand() {
        String input = "Hello & World";
        String expectedOutput = "Hello &amp; World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithLessThanSign() {
        String input = "Hello < World";
        String expectedOutput = "Hello &lt; World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithGreaterThanSign() {
        String input = "Hello > World";
        String expectedOutput = "Hello &gt; World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithMultipleSpecialChars() {
        String input = "Hello \"<>&\" World";
        String expectedOutput = "Hello &quot;&lt;&gt;&amp;&quot; World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithExtendedAscii() {
        String input = "Hello \u00C0 World"; // À
        String expectedOutput = "Hello &#192; World";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithoutSpecialChars() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String expectedOutput = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }

    @Test
    void testStringWithAllSpecialChars() {
        String input = "<>&\"";
        String expectedOutput = "&lt;&gt;&amp;&quot;";
        assertEquals(expectedOutput, HtmlEncoder.encode(input));
    }
}
```

To run these tests, you will need to ensure you have JUnit 5 available in your project. This test suite covers various cases including strings with no special characters, individual special characters, combinations of special characters, and Unicode characters outside the 7-bit ASCII range.