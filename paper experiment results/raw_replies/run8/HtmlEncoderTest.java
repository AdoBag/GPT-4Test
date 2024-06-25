```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HtmlEncoderTest {

    @Test
    public void testEncode_emptyString() {
        assertEquals("", HtmlEncoder.encode(""));
    }

    @Test
    public void testEncode_noSpecialCharacters() {
        assertEquals("HelloWorld", HtmlEncoder.encode("HelloWorld"));
    }

    @Test
    public void testEncode_withDoubleQuote() {
        assertEquals("Hello&quot;World", HtmlEncoder.encode("Hello\"World"));
    }

    @Test
    public void testEncode_withAmpersand() {
        assertEquals("Hello&amp;World", HtmlEncoder.encode("Hello&World"));
    }

    @Test
    public void testEncode_withLessThan() {
        assertEquals("Hello&lt;World", HtmlEncoder.encode("Hello<World"));
    }

    @Test
    public void testEncode_withGreaterThan() {
        assertEquals("Hello&gt;World", HtmlEncoder.encode("Hello>World"));
    }

    @Test
    public void testEncode_withMultipleSpecialCharacters() {
        assertEquals("&lt;div class=&quot;test&quot;&gt;Hello &amp; Welcome&lt;/div&gt;", HtmlEncoder.encode("<div class=\"test\">Hello & Welcome</div>"));
    }

    @Test
    public void testEncode_withNonAsciiCharacter() {
        assertEquals("Hello&#169;World", HtmlEncoder.encode("Hello©World"));
    }

    @Test
    public void testEncode_withMixedCharacters() {
        assertEquals("&lt;Hello&gt;&amp;&quot;World&quot;&#169;", HtmlEncoder.encode("<Hello>&\"World\"©"));
    }

    @Test
    public void testEncode_withUnicode() {
        assertEquals("Unicode&#169;", HtmlEncoder.encode("Unicode©"));
    }

    @Test
    public void testEncode_boundaryCharacter() {
        assertEquals("&#x27;", HtmlEncoder.encode("\u0027"));
    }
}
```