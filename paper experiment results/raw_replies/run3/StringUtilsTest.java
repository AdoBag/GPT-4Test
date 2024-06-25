```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        assertEquals("  foo", StringUtils.leftPad("foo", 5));
        assertEquals("foo", StringUtils.leftPad("foo", 2)); // no padding
        assertEquals("foobar", StringUtils.leftPad("foobar", 6)); // no padding needed
        assertEquals("foobar", StringUtils.leftPad("foobar", 2)); // no padding needed, already longer
        assertEquals("foobar  ", StringUtils.leftPad("foobar  ", 8)); // already padded
    }

    @Test
    public void testSplitByLines() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{"a", "b", "", "c"}, StringUtils.splitByLines("a\nb\n\nc"));
        assertArrayEquals(new String[]{"a"}, StringUtils.splitByLines("a"));
        assertArrayEquals(new String[]{"a", "b\nc"}, StringUtils.splitByLines("a\nb\nc"));
    }

    @Test
    public void testSplitBy() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b", "", "c"}, StringUtils.splitBy("a,b,,c", ','));
        assertArrayEquals(new String[]{"a"}, StringUtils.splitBy("a", ','));
        assertArrayEquals(new String[]{"a", "b,c"}, StringUtils.splitBy("a,b,c", ','));
    }

    @Test
    public void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{1}, StringUtils.extractInts("1"));
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        assertArrayEquals(new int[]{1, 0, -1}, StringUtils.extractInts("1 0 -1"));
    }

    @Test
    public void testEscapeForXML() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("&amp;&amp;&amp;", StringUtils.escapeForXML("&&&"));
        assertEquals("hello &quot;world&quot;", StringUtils.escapeForXML("hello \"world\""));
        assertEquals("It's &apos;me&apos;", StringUtils.escapeForXML("It's 'me'"));
        assertEquals("Control char: &#x0004;", StringUtils.escapeForXML("Control char: " + (char) 4));
    }

    @Test
    public void testSubstitute() {
        assertEquals("hello world", StringUtils.substitute("hello foo", "foo", "world"));
        assertEquals("hello world world", StringUtils.substitute("hello world foo", "foo", "world"));
        assertEquals("foo world", StringUtils.substitute("foo foo", "foo", "world"));
        assertEquals("foo", StringUtils.substitute("foo", "bar", "world")); // no substitution
        assertEquals("", StringUtils.substitute("", "bar", "world")); // empty string
    }
}
```

This test suite covers `leftPad`, `splitByLines`, `splitBy`, `extractInts`, `escapeForXML`, and `substitute` methods with various assertions to validate their behavior. You can use a testing framework like JUnit 5 (Jupiter) to run these tests.