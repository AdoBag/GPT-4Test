```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class HTMLFilterTest {

    @Test
    public void testNullMessage() {
        String result = HTMLFilter.filter(null);
        assertNull(result);
    }

    @Test
    public void testEmptyMessage() {
        String result = HTMLFilter.filter("");
        assertEquals("", result);
    }

    @Test
    public void testMessageWithNoSpecialCharacters() {
        String result = HTMLFilter.filter("This is a test message.");
        assertEquals("This is a test message.", result);
    }

    @Test
    public void testMessageWithLessThan() {
        String result = HTMLFilter.filter("This is a < test message.");
        assertEquals("This is a &lt; test message.", result);
    }

    @Test
    public void testMessageWithGreaterThan() {
        String result = HTMLFilter.filter("This is a > test message.");
        assertEquals("This is a &gt; test message.", result);
    }

    @Test
    public void testMessageWithAmpersand() {
        String result = HTMLFilter.filter("This is a & test message.");
        assertEquals("This is a &amp; test message.", result);
    }

    @Test
    public void testMessageWithDoubleQuote() {
        String result = HTMLFilter.filter("This is a \" test message.");
        assertEquals("This is a &quot; test message.", result);
    }

    @Test
    public void testMessageWithAllSpecialCharacters() {
        String result = HTMLFilter.filter("<>&\"");
        assertEquals("&lt;&gt;&amp;&quot;", result);
    }

    @Test
    public void testMessageWithMixedCharacters() {
        String result = HTMLFilter.filter("Hello <World> & \"Java\"");
        assertEquals("Hello &lt;World&gt; &amp; &quot;Java&quot;", result);
    }

    @Test
    public void testMessageWithRepeatedSpecialCharacters() {
        String result = HTMLFilter.filter("<<>>&&\"\"");
        assertEquals("&lt;&lt;&gt;&gt;&amp;&amp;&quot;&quot;", result);
    }
}
```
