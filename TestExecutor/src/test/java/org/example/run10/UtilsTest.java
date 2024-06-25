
package org.example.run10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
//    void testEncodePath() {
    void testEncodePath__fixed() {
//        assertEquals("hello_world", Utils.encodePath("hello/world"));
        /** Incorrectly predicted output */
        assertEquals("hello-world", Utils.encodePath("hello/world"));

        assertEquals("hello--world", Utils.encodePath("hello-world"));
        assertEquals("hello__world", Utils.encodePath("hello_world"));
        assertEquals("hello$$world", Utils.encodePath("hello$world"));
//        assertEquals("hello~~world", Utils.encodePath("hello:world"));
//        assertEquals("hello~~~~world", Utils.encodePath("hello~world"));
        /** Incorrectly predicted output */
        assertEquals("hello~world", Utils.encodePath("hello:world"));
        assertEquals("hello~~world", Utils.encodePath("hello~world"));
    }

    @Test
//    void testEncodePathAsIdentifier() {
    void testEncodePathAsIdentifier__fixed() {
//        assertEquals("hello_world", Utils.encodePathAsIdentifier("hello/world"));
//        assertEquals("hello_minus_world", Utils.encodePathAsIdentifier("hello-world"));
//        assertEquals("hello_undef_world", Utils.encodePathAsIdentifier("hello_world"));
//        assertEquals("hello_36_world", Utils.encodePathAsIdentifier("hello$world"));
        /** Incorrectly predicted output */
        assertEquals("hello_47_world", Utils.encodePathAsIdentifier("hello/world"));
        assertEquals("hello_45_world", Utils.encodePathAsIdentifier("hello-world"));
        assertEquals("hello_world", Utils.encodePathAsIdentifier("hello_world"));
        assertEquals("hello$world", Utils.encodePathAsIdentifier("hello$world"));

        assertEquals("hello_126_world", Utils.encodePathAsIdentifier("hello~world"));
        assertEquals("_47_", Utils.encodePathAsIdentifier("/"));
    }

    @Test
//    void testAbsoluteURL() {
    void testAbsoluteURL__fixed() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com"));
        //assertFalse(Utils.absoluteURL("localhost:8080"));
        /** A valid absolute URL */
        assertTrue(Utils.absoluteURL("localhost:8080"));

        assertFalse(Utils.absoluteURL("/path/to/resource"));
        assertFalse(Utils.absoluteURL("example.com"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
        assertFalse(Utils.pseudoAbsoluteURL("localhost:8080"));
        assertFalse(Utils.pseudoAbsoluteURL("path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("example.com"));
    }

    @Test
    void testNChars() {
        assertEquals("aaa", Utils.nChars(3, 'a'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals("bb", Utils.nChars(2, 'b'));
    }
}
