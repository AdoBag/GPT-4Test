```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testEncodePath() {
        assertEquals("abc--def", Utils.encodePath("abc-def"));
        assertEquals("abc__def", Utils.encodePath("abc_def"));
        assertEquals("abc$$def", Utils.encodePath("abc$def"));
        assertEquals("abc~~def", Utils.encodePath("abc~def"));
        assertEquals("abc-def", Utils.encodePath("abc/def"));
        assertEquals("abc-def", Utils.encodePath("abc\\def"));
        assertEquals("abc_def", Utils.encodePath("abc*def"));
        assertEquals("abc$def", Utils.encodePath("abc?def"));
        assertEquals("abc~def", Utils.encodePath("abc:def"));
    }

    @Test
    void testEncodePathAsIdentifier() {
        assertEquals("abc_def", Utils.encodePathAsIdentifier("abc/def"));
        assertEquals("_47_abc", Utils.encodePathAsIdentifier("/abc"));
        assertEquals("a_47_b", Utils.encodePathAsIdentifier("a/b"));
        assertEquals("a_b_c", Utils.encodePathAsIdentifier("a/b*c"));
    }

    @Test
    void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("mailto:user@example.com"));
        assertFalse(Utils.absoluteURL("/path/to/resource"));
        assertFalse(Utils.absoluteURL("path/to/resource"));
        assertFalse(Utils.absoluteURL(""));
        assertFalse(Utils.absoluteURL("/"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
        assertFalse(Utils.pseudoAbsoluteURL("path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
        assertTrue(Utils.pseudoAbsoluteURL("/"));
    }

    @Test
    void testNChars() {
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals("a", Utils.nChars(1, 'a'));
        assertEquals("aaa", Utils.nChars(3, 'a'));
        assertEquals("bbbbb", Utils.nChars(5, 'b'));
    }
}
```