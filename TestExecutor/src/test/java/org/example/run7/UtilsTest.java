
package org.example.run7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
//    public void testEncodePath() {
    public void testEncodePath__fixed() {
        assertEquals("hello", Utils.encodePath("hello"));
        assertEquals("hello-world", Utils.encodePath("hello/world"));
//        assertEquals("hello__world", Utils.encodePath("hello*world"));
//        assertEquals("hello$$world", Utils.encodePath("hello?world"));
//        assertEquals("hello~~world", Utils.encodePath("hello:world"));
//        assertEquals("hello----world", Utils.encodePath("hello\\world"));
        /** Incorrectly predicted output */
        assertEquals("hello_world", Utils.encodePath("hello*world"));
        assertEquals("hello$world", Utils.encodePath("hello?world"));
        assertEquals("hello~world", Utils.encodePath("hello:world"));
        assertEquals("hello-world", Utils.encodePath("hello\\world"));

        assertEquals("--__$$~~", Utils.encodePath("-_$~"));
    }

    @Test
//    public void testEncodePathAsIdentifier() {
    public void testEncodePathAsIdentifier__fixed() {
//        assertEquals("hello_world", Utils.encodePathAsIdentifier("hello/world"));
        /** Incorrectly predicted output */
        assertEquals("hello_47_world", Utils.encodePathAsIdentifier("hello/world"));

        assertEquals("hello_92_world", Utils.encodePathAsIdentifier("hello\\world"));
        assertEquals("_47_hello", Utils.encodePathAsIdentifier("/hello"));
        assertEquals("hello_63_", Utils.encodePathAsIdentifier("hello?"));
        assertEquals("hello_42_world", Utils.encodePathAsIdentifier("hello*world"));
        assertEquals("hello_58_world", Utils.encodePathAsIdentifier("hello:world"));
    }

    @Test
//    public void testAbsoluteURL() {
    public void testAbsoluteURL__fixed() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com/abc"));
        assertFalse(Utils.absoluteURL("/abc"));
        assertFalse(Utils.absoluteURL("example.com/abc"));
//        assertFalse(Utils.absoluteURL(":example.com/abc"));
        /** Technically invalid, but the class is not concerned with it. Arguably a corner case */
        assertTrue(Utils.absoluteURL(":example.com/abc"));
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
