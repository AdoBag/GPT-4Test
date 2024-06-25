```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        assertEquals("   test", StringUtils.leftPad("test", 7));
        assertEquals("test", StringUtils.leftPad("test", 4));
        assertEquals("test", StringUtils.leftPad("test", 2));
        assertEquals("", StringUtils.leftPad("", 0));
        assertEquals(" ", StringUtils.leftPad("", 1));
    }

    @Test
    public void testSplitByLines() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{"a", "b", "c", ""}, StringUtils.splitByLines("a\nb\nc\n"));
        assertArrayEquals(new String[]{"", "a", "b", "c"}, StringUtils.splitByLines("\na\nb\nc"));
        assertArrayEquals(new String[]{""}, StringUtils.splitByLines(""));
    }

    @Test
    public void testSplitBy() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b", "c", ""}, StringUtils.splitBy("a,b,c,", ','));
        assertArrayEquals(new String[]{"", "a", "b", "c"}, StringUtils.splitBy(",a,b,c", ','));
        assertArrayEquals(new String[]{""}, StringUtils.splitBy("", ','));
    }

    @Test
    public void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        assertArrayEquals(new int[]{42}, StringUtils.extractInts("42"));
        assertThrows(NumberFormatException.class, () -> StringUtils.extractInts("not a number"));
    }

    @Test
    public void testEscapeForXML() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("name &amp; value", StringUtils.escapeForXML("name & value"));
        assertEquals("quote: &quot;", StringUtils.escapeForXML("quote: \""));
        assertEquals("apos: &apos;", StringUtils.escapeForXML("apos: '"));
        assertEquals("normal text", StringUtils.escapeForXML("normal text"));
        assertEquals("control &#x000d;&#x000a;", StringUtils.escapeForXML("control \r\n"));
        assertEquals("ILLEGAL-XML-CHAR:&#x0007;", StringUtils.escapeForXML("\u0007"));
    }

    @Test
    public void testSubstitute() {
        assertEquals("hello world", StringUtils.substitute("hello there", "there", "world"));
        assertEquals("hello world hello", StringUtils.substitute("hello there hello", "there", "world"));
        assertEquals("no match", StringUtils.substitute("no match", "yes", "no"));
        assertEquals("nothing to replace", StringUtils.substitute("nothing to replace", "", "test"));
        assertEquals("empty source and target", StringUtils.substitute("empty source and target", "", ""));
    }
}
```