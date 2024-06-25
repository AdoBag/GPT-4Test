```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testEncodePath() {
        assertEquals("a_b_c_d", Utils.encodePath("a*b/c:d"));
        assertEquals("foo~bar", Utils.encodePath("foo:bar"));
        assertEquals("path-name", Utils.encodePath("path\\name"));
        assertEquals("hello--world", Utils.encodePath("hello-world"));
        assertEquals("file$$name", Utils.encodePath("file?name"));
        assertEquals("double__underscore", Utils.encodePath("double*underscore"));
    }

    @Test
    void testEncodePathAsIdentifier() {
        assertEquals("a_b_c_d", Utils.encodePathAsIdentifier("a*b/c:d"));
        assertEquals("foo_102_bar", Utils.encodePathAsIdentifier("foo:bar"));
        assertEquals("path_92_name", Utils.encodePathAsIdentifier("path\\name"));
        assertEquals("hello_45_46_world", Utils.encodePathAsIdentifier("hello-.world"));
        assertEquals("file_63_name", Utils.encodePathAsIdentifier("file?name"));
        assertEquals("_49_double_92_", Utils.encodePathAsIdentifier("1double\\"));
    }

    @Test
    void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("mailto:someone@example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
        assertFalse(Utils.absoluteURL("/path/to/resource"));
        assertFalse(Utils.absoluteURL("server:path/to/resource"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
    }

    @Test
    void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("-----", Utils.nChars(5, '-'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals(" ", Utils.nChars(1, ' '));
    }
}
```