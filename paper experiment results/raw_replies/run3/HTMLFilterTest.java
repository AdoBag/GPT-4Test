```java
package org.example.run1;

import org.junit.Test;
import static org.junit.Assert.*;

public class HTMLFilterTest {

    @Test
    public void testNullInput() {
        assertNull(HTMLFilter.filter(null));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", HTMLFilter.filter(""));
    }

    @Test
    public void testNoSpecialCharacters() {
        assertEquals("hello world", HTMLFilter.filter("hello world"));
    }

    @Test
    public void testLessThanCharacter() {
        assertEquals("&lt;script&gt;", HTMLFilter.filter("<script>"));
    }

    @Test
    public void testGreaterThanCharacter() {
        assertEquals("&lt;/script&gt;", HTMLFilter.filter("</script>"));
    }

    @Test
    public void testAmpersandCharacter() {
        assertEquals("this &amp; that", HTMLFilter.filter("this & that"));
    }

    @Test
    public void testQuoteCharacter() {
        assertEquals("&quot;quoted text&quot;", HTMLFilter.filter("\"quoted text\""));
    }

    @Test
    public void testMixedSpecialCharacters() {
        assertEquals("&lt;div class=&quot;example&quot;&gt;hello &amp; welcome&lt;/div&gt;", 
                     HTMLFilter.filter("<div class=\"example\">hello & welcome</div>"));
    }

    @Test
    public void testMultipleSameSpecialCharacters() {
        assertEquals("1 &amp; 1 &amp; 1", HTMLFilter.filter("1 & 1 & 1"));
    }

    @Test
    public void testNoSpecialCharactersInLongString() {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt.";
        assertEquals(input, HTMLFilter.filter(input));
    }

    @Test
    public void testAllSpecialCharacters() {
        String input = "<>&\"";
        String expectedOutput = "&lt;&gt;&amp;&quot;";
        assertEquals(expectedOutput, HTMLFilter.filter(input));
    }
}
```