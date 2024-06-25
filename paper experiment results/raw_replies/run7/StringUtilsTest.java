```java
package org.example.run1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        Assertions.assertEquals("  foo", StringUtils.leftPad("foo", 5));
        Assertions.assertEquals("foo", StringUtils.leftPad("foo", 3));
        Assertions.assertEquals("foo", StringUtils.leftPad("foo", 2));
    }

    @Test
    public void testSplitByLines() {
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        Assertions.assertArrayEquals(new String[]{"a", "b", "c", ""}, StringUtils.splitByLines("a\nb\nc\n"));
        Assertions.assertArrayEquals(new String[]{"abc"}, StringUtils.splitByLines("abc"));
    }

    @Test
    public void testSplitBy() {
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a.b.c", '.'));
        Assertions.assertArrayEquals(new String[]{"abc"}, StringUtils.splitBy("abc", '.'));
        Assertions.assertArrayEquals(new String[]{"", "b", "c"}, StringUtils.splitBy(".b.c", '.'));
    }

    @Test
    public void testExtractInts() {
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        Assertions.assertArrayEquals(new int[]{10, 20, 30}, StringUtils.extractInts("10 20 30"));
        Assertions.assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        Assertions.assertThrows(NumberFormatException.class, () -> StringUtils.extractInts("1 2 a"));
    }

    @Test
    public void testEscapeForXML() {
        Assertions.assertEquals("&lt;a&gt;&amp;&quot;test&quot;&apos;", StringUtils.escapeForXML("<a>&\"test'"));
        Assertions.assertEquals("hello", StringUtils.escapeForXML("hello"));
        Assertions.assertEquals("&lt;hello&gt;", StringUtils.escapeForXML("<hello>"));
        Assertions.assertEquals("&#x03;illegal", StringUtils.escapeForXML("\u0003illegal"));
    }

    @Test
    public void testSubstitute() {
        Assertions.assertEquals("hello world", StringUtils.substitute("hello there", "there", "world"));
        Assertions.assertEquals("12345", StringUtils.substitute("12345", "67", "89"));
        Assertions.assertEquals("hello there", StringUtils.substitute("hello there", "world", "planet"));
    }
}
```