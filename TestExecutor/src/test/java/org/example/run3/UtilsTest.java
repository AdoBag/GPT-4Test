
package org.example.run3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
//    public void testEncodePath() {
    public void testEncodePath__fixed() {
        assertEquals("--hello", Utils.encodePath("-hello"));
        assertEquals("hello--world", Utils.encodePath("hello-world"));
        assertEquals("__testing__", Utils.encodePath("_testing_"));
        assertEquals("path$$name", Utils.encodePath("path$name"));
        assertEquals("dir~name", Utils.encodePath("dir:name"));
        assertEquals("dir-name", Utils.encodePath("dir/name"));
//        assertEquals("escaped\\backslash", Utils.encodePath("escaped\\backslash"));
//        assertEquals("__star__", Utils.encodePath("*star*"));
        /** Incorrectly predicted output */
        assertEquals("escaped-backslash", Utils.encodePath("escaped\\backslash"));
        assertEquals("_star_", Utils.encodePath("*star*"));

        assertEquals("$question$", Utils.encodePath("?question?"));
    }

    @Test
//    public void testEncodePathAsIdentifier() {
    public void testEncodePathAsIdentifier__fixed() {
//        assertEquals("_57_", Utils.encodePathAsIdentifier("9abc"));
        /** Incorrectly predicted output */
        assertEquals("_57_abc", Utils.encodePathAsIdentifier("9abc"));

        assertEquals("abc_35_", Utils.encodePathAsIdentifier("abc#"));
//        assertEquals("_115_test", Utils.encodePathAsIdentifier("s_test"));
        /** Incorrectly predicted output */
        assertEquals("s_test", Utils.encodePathAsIdentifier("s_test"));

        assertEquals("_47_root", Utils.encodePathAsIdentifier("/root"));
        assertEquals("validIdentifier", Utils.encodePathAsIdentifier("validIdentifier"));
    }

    @Test
//    public void testAbsoluteURL() {
    public void testAbsoluteURL__fixed() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("file:///path/to/file"));
        assertFalse(Utils.absoluteURL("example.com/path"));
        assertFalse(Utils.absoluteURL("/path/to/resource"));
//        assertFalse(Utils.absoluteURL("example:com/path")); // colon after the first segment
        /** This is a valid absolute URL */
        assertTrue(Utils.absoluteURL("example:com/path")); // colon after the first segment
    }

    @Test
    public void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/absolute/path"));
        assertFalse(Utils.pseudoAbsoluteURL("relative/path"));
        assertFalse(Utils.pseudoAbsoluteURL("file:///absolute/path"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
    }

    @Test
//    public void testNChars() {
    public void testNChars__fixed() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
//        assertEquals("12345", Utils.nChars(5, '1'));
        /** Incorrectly predicted output */
        assertEquals("11111", Utils.nChars(5, '1'));
        assertEquals("*****", Utils.nChars(5, '*'));
        assertEquals("", Utils.nChars(0, 'a')); // edge case for 0
    }
}
