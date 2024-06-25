```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testLeftPad() {
        assertEquals("  hello", StringUtils.leftPad("hello", 7));
        assertEquals("hello", StringUtils.leftPad("hello", 5));
        assertEquals("hello", StringUtils.leftPad("hello", 3));
    }

    @Test
    void testSplitByLines() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{""}, StringUtils.splitByLines(""));
        assertArrayEquals(new String[]{"a", "", "b", "c"}, StringUtils.splitByLines("a\n\nb\nc"));
    }

    @Test
    void testSplitBy() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a b c", ' '));
        assertArrayEquals(new String[]{""}, StringUtils.splitBy("", ','));
    }

    @Test
    void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        assertArrayEquals(new int[]{1, -2, 3}, StringUtils.extractInts("1 -2 3"));
    }

    @Test
    void testEscapeForXML() {
        assertEquals("&lt;div&gt;&amp;&quot;text&quot;&amp;&apos;&lt;/div&gt;", StringUtils.escapeForXML("<div>&\"text\"&'<div>"));
        assertEquals("Sample text without special characters", StringUtils.escapeForXML("Sample text without special characters"));
        assertEquals("ILLEGAL-XML-CHAR:", StringUtils.escapeForXML("\u0003")); // Non-printable character
    }

    @Test
    void testSubstitute() {
        assertEquals("hello world", StringUtils.substitute("hello java", "java", "world"));
        assertEquals("hello world world", StringUtils.substitute("hello world java", "java", "world"));
        assertEquals("hello java", StringUtils.substitute("hello java", "python", "world")); // substring not present
    }
}
```