
package org.example.run4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
//    void testEncodePath() {
    void testEncodePath__fixed() {
//        assertEquals("foo--bar", Utils.encodePath("foo/bar"));
//        assertEquals("foo-__bar", Utils.encodePath("foo\\bar"));
//        assertEquals("foo_$", Utils.encodePath("foo?"));
//        assertEquals("foo~~bar", Utils.encodePath("foo:bar"));
//        assertEquals("foo--~__$$~~bar", Utils.encodePath("foo--:_bar"));
        /** Incorrectly predicted output */
        assertEquals("foo-bar", Utils.encodePath("foo/bar"));
        assertEquals("foo-bar", Utils.encodePath("foo\\bar"));
        assertEquals("foo$", Utils.encodePath("foo?"));
        assertEquals("foo~bar", Utils.encodePath("foo:bar"));
        assertEquals("foo----~__bar", Utils.encodePath("foo--:_bar"));

        assertEquals("abcd", Utils.encodePath("abcd"));
    }

    @Test
//    void testEncodePathAsIdentifier() {
    void testEncodePathAsIdentifier__fixed() {
//        assertEquals("foo_bar", Utils.encodePathAsIdentifier("foo/bar"));
        /** Incorrectly predicted output */
        assertEquals("foo_47_bar", Utils.encodePathAsIdentifier("foo/bar"));

        assertEquals("foo_92_bar", Utils.encodePathAsIdentifier("foo\\bar"));
        assertEquals("foo_63_", Utils.encodePathAsIdentifier("foo?"));
        assertEquals("foo_58_bar", Utils.encodePathAsIdentifier("foo:bar"));
        assertEquals("_47_foo", Utils.encodePathAsIdentifier("/foo"));
//        assertEquals("foo_bar_64_", Utils.encodePathAsIdentifier("foo/bar@"));
        /** Incorrectly predicted output */
        assertEquals("foo_47_bar_64_", Utils.encodePathAsIdentifier("foo/bar@"));

        assertEquals("abcd", Utils.encodePathAsIdentifier("abcd"));
    }

    @Test
//    void testAbsoluteURL() {
    void testAbsoluteURL__fixed() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
        assertTrue(Utils.absoluteURL("mailto:user@example.com"));
        assertTrue(Utils.absoluteURL("file:///example.txt"));
        assertFalse(Utils.absoluteURL("/example.txt"));
//        assertFalse(Utils.absoluteURL("example:test"));
        /** According to the code, this is a valid URL (like localhost:8080) */
        assertTrue(Utils.absoluteURL("example:test"));
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
//    void testNChars() {
    void testNChars__fixed() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals(" ", Utils.nChars(1, ' '));
        assertEquals("bbbbb", Utils.nChars(5, 'b'));
//        assertEquals("xyzxyz", Utils.nChars(2, 'x') + Utils.nChars(2, 'y') + Utils.nChars(2, 'z'));
        /** Incorrectly predicted output */
        assertEquals("xxyyzz", Utils.nChars(2, 'x') + Utils.nChars(2, 'y') + Utils.nChars(2, 'z'));
    }
}
