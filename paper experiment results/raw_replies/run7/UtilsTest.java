```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void testEncodePath() {
        assertEquals("hello", Utils.encodePath("hello"));
        assertEquals("hello-world", Utils.encodePath("hello/world"));
        assertEquals("hello__world", Utils.encodePath("hello*world"));
        assertEquals("hello$$world", Utils.encodePath("hello?world"));
        assertEquals("hello~~world", Utils.encodePath("hello:world"));
        assertEquals("hello----world", Utils.encodePath("hello\\world"));
        assertEquals("--__$$~~", Utils.encodePath("-_$~"));
    }

    @Test
    public void testEncodePathAsIdentifier() {
        assertEquals("hello_world", Utils.encodePathAsIdentifier("hello/world"));
        assertEquals("hello_92_world", Utils.encodePathAsIdentifier("hello\\world"));
        assertEquals("_47_hello", Utils.encodePathAsIdentifier("/hello"));
        assertEquals("hello_63_", Utils.encodePathAsIdentifier("hello?"));
        assertEquals("hello_42_world", Utils.encodePathAsIdentifier("hello*world"));
        assertEquals("hello_58_world", Utils.encodePathAsIdentifier("hello:world"));
    }

    @Test
    public void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com/abc"));
        assertFalse(Utils.absoluteURL("/abc"));
        assertFalse(Utils.absoluteURL("example.com/abc"));
        assertFalse(Utils.absoluteURL(":example.com/abc"));
    }

    @Test
    public void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/abc"));
        assertFalse(Utils.pseudoAbsoluteURL("abc/def"));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com/abc"));
        assertTrue(Utils.pseudoAbsoluteURL("/"));
    }

    @Test
    public void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("bb", Utils.nChars(2, 'b'));
        assertEquals("", Utils.nChars(0, 'c'));
        assertEquals("cccccccccc", Utils.nChars(10, 'c'));
    }
}
```