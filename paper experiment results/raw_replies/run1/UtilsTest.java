```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void testEncodePath() {
        assertEquals("path-name", Utils.encodePath("path/name"));
        assertEquals("path_name", Utils.encodePath("path*name"));
        assertEquals("path$name", Utils.encodePath("path?name"));
        assertEquals("path~name", Utils.encodePath("path:name"));
        assertEquals("------", Utils.encodePath("----"));
    }

    @Test
    void testEncodePathAsIdentifier() {
        assertEquals("path_name", Utils.encodePathAsIdentifier("path*name"));
        assertEquals("_47_test_47_path_45_", Utils.encodePathAsIdentifier("///test/path-"));
        assertEquals("path______name", Utils.encodePathAsIdentifier("path\\\\name"));
        assertEquals("path_46_name", Utils.encodePathAsIdentifier("path.name"));
    }

    @Test
    void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
        assertFalse(Utils.absoluteURL("/path/to/resource"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
        assertFalse(Utils.pseudoAbsoluteURL("path/to/resource"));
    }

    @Test
    void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("bbbbb", Utils.nChars(5, 'b'));
        assertEquals("", Utils.nChars(0, 'a'));
    }
}
```