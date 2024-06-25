```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        assertEquals("   text", StringUtils.leftPad("text", 7));
        assertEquals("text", StringUtils.leftPad("text", 4));
        assertEquals("text", StringUtils.leftPad("text", 3));
        assertEquals("  ", StringUtils.leftPad("", 2));
    }

    @Test
    public void testSplitByLines() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{"a", "b", ""}, StringUtils.splitByLines("a\nb\n"));
        assertArrayEquals(new String[]{""}, StringUtils.splitByLines(""));
        assertArrayEquals(new String[]{"line1", "line2", "line3"}, StringUtils.splitByLines("line1\nline2\nline3"));
    }

    @Test
    public void testSplitBy() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b", ""}, StringUtils.splitBy("a,b,", ','));
        assertArrayEquals(new String[]{""}, StringUtils.splitBy("", ','));
        assertArrayEquals(new String[]{"word1", "word2", "word3"}, StringUtils.splitBy("word1.word2.word3", '.'));
    }

    @Test
    public void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{10, 20, 30}, StringUtils.extractInts("10 20 30"));
        assertArrayEquals(new int[]{999, 123}, StringUtils.extractInts("999 123"));
        assertArrayEquals(new int[]{0}, StringUtils.extractInts("0"));
    }

    @Test
    public void testEscapeForXML() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("AT&amp;T", StringUtils.escapeForXML("AT&T"));
        assertEquals("John&apos;s book", StringUtils.escapeForXML("John's book"));
        assertEquals("3 &lt; 5 and 5 &gt; 3", StringUtils.escapeForXML("3 < 5 and 5 > 3"));
        assertEquals("A &quot;quote&quot;", StringUtils.escapeForXML("A \"quote\""));
    }

    @Test
    public void testSubstitute() {
        assertEquals("hello world", StringUtils.substitute("hello there", "there", "world"));
        assertEquals("say hello", StringUtils.substitute("say bye", "bye", "hello"));
        assertEquals("nothing to replace", StringUtils.substitute("nothing to replace", "not present", "replace"));
        assertEquals("", StringUtils.substitute("", "source", "target"));
        assertEquals("repeat repeat", StringUtils.substitute("repeat repeat repeat", "repeat repeat", "repeat"));
    }
}
```

This JUnit 5 test suite comprehensively tests all public static methods in the `StringUtils` class. Each method is tested with various inputs to ensure they behave as expected.