```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testEncodePath() {
        assertEquals("foo--bar", Utils.encodePath("foo/bar"));
        assertEquals("foo-__bar", Utils.encodePath("foo\\bar"));
        assertEquals("foo_$", Utils.encodePath("foo?"));
        assertEquals("foo~~bar", Utils.encodePath("foo:bar"));
        assertEquals("foo--~__$$~~bar", Utils.encodePath("foo--:_bar"));
        assertEquals("abcd", Utils.encodePath("abcd"));
    }

    @Test
    void testEncodePathAsIdentifier() {
        assertEquals("foo_bar", Utils.encodePathAsIdentifier("foo/bar"));
        assertEquals("foo_92_bar", Utils.encodePathAsIdentifier("foo\\bar"));
        assertEquals("foo_63_", Utils.encodePathAsIdentifier("foo?"));
        assertEquals("foo_58_bar", Utils.encodePathAsIdentifier("foo:bar"));
        assertEquals("_47_foo", Utils.encodePathAsIdentifier("/foo"));
        assertEquals("foo_bar_64_", Utils.encodePathAsIdentifier("foo/bar@"));
        assertEquals("abcd", Utils.encodePathAsIdentifier("abcd"));
    }

    @Test
    void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
        assertTrue(Utils.absoluteURL("mailto:user@example.com"));
        assertTrue(Utils.absoluteURL("file:///example.txt"));
        assertFalse(Utils.absoluteURL("/example.txt"));
        assertFalse(Utils.absoluteURL("example:test"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/example"));
        assertFalse(Utils.pseudoAbsoluteURL("example"));
        assertTrue(Utils.pseudoAbsoluteURL("/"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
    }

    @Test
    void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals(" ", Utils.nChars(1, ' '));
        assertEquals("bbbbb", Utils.nChars(5, 'b'));
        assertEquals("xyzxyz", Utils.nChars(2, 'x') + Utils.nChars(2, 'y') + Utils.nChars(2, 'z'));
    }
}
```