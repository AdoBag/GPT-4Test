
package org.example.run6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void testEncodePath() {
        assertEquals("hello--world", Utils.encodePath("hello-world"));
        assertEquals("hello__world", Utils.encodePath("hello_world"));
        assertEquals("hello$$world", Utils.encodePath("hello$world"));
        assertEquals("hello~~world", Utils.encodePath("hello~world"));
        assertEquals("hello-world", Utils.encodePath("hello/world"));
        assertEquals("hello-world", Utils.encodePath("hello\\world"));
        assertEquals("hello_world", Utils.encodePath("hello*world"));
        assertEquals("hello$world", Utils.encodePath("hello?world"));
        assertEquals("hello~world", Utils.encodePath("hello:world"));
    }

    @Test
//    public void testEncodePathAsIdentifier() {
    public void testEncodePathAsIdentifier__fixed() {
//        assertEquals("hello_world", Utils.encodePathAsIdentifier("hello world"));
        /** Incorrectly predicted output */
        assertEquals("hello_32_world", Utils.encodePathAsIdentifier("hello world"));

        assertEquals("hello_47_world", Utils.encodePathAsIdentifier("hello/world"));
//        assertEquals("_104_hello", Utils.encodePathAsIdentifier("&hello"));
//        assertEquals("hello___world", Utils.encodePathAsIdentifier("hello@#world"));
        /** Incorrectly predicted output */
        assertEquals("_38_hello", Utils.encodePathAsIdentifier("&hello"));
        assertEquals("hello_64__35_world", Utils.encodePathAsIdentifier("hello@#world"));
    }

    @Test
    public void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
        assertTrue(Utils.absoluteURL("abc:123"));
        assertFalse(Utils.absoluteURL("/abc/123"));
    }

    @Test
    public void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/absolute/path"));
        assertFalse(Utils.pseudoAbsoluteURL("relative/path"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
    }

    @Test
    public void testNChars() {
        assertEquals("aaa", Utils.nChars(3, 'a'));
        assertEquals("", Utils.nChars(0, 'b'));
        assertEquals("%%%%%", Utils.nChars(5, '%'));
    }
}
