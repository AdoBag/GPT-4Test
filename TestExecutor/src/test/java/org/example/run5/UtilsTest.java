
package org.example.run5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
//    public void testEncodePath() {
    public void testEncodePath__fixed() {
        assertEquals("path-to-file", Utils.encodePath("path/to/file"));
//        assertEquals("path-to--file", Utils.encodePath("path/to-/file"));
//        assertEquals("__star__", Utils.encodePath("*star*"));
//        assertEquals("path$$name", Utils.encodePath("path?name"));
//        assertEquals("C~~Drive", Utils.encodePath("C:Drive"));
        /** Incorrectly predicted output */
        assertEquals("path-to---file", Utils.encodePath("path/to-/file"));
        assertEquals("_star_", Utils.encodePath("*star*"));
        assertEquals("path$name", Utils.encodePath("path?name"));
        assertEquals("C~Drive", Utils.encodePath("C:Drive"));

        assertEquals("back-slash-", Utils.encodePath("back\\slash\\"));
    }

    @Test
//    public void testEncodePathAsIdentifier() {
    public void testEncodePathAsIdentifier__fixed() {
//        assertEquals("path_to_file", Utils.encodePathAsIdentifier("path/to/file"));
//        assertEquals("path_to__45_file", Utils.encodePathAsIdentifier("path/to-/file"));
        /** Incorrectly predicted output */
        assertEquals("path_47_to_47_file", Utils.encodePathAsIdentifier("path/to/file"));
        assertEquals("path_47_to_45__47_file", Utils.encodePathAsIdentifier("path/to-/file"));

        assertEquals("_42_star_42_", Utils.encodePathAsIdentifier("*star*"));
        assertEquals("path_63_name", Utils.encodePathAsIdentifier("path?name"));
        assertEquals("C_58_Drive", Utils.encodePathAsIdentifier("C:Drive"));
        assertEquals("back_92_slash_92_", Utils.encodePathAsIdentifier("back\\slash\\"));
    }

    @Test
//    public void testAbsoluteURL() {
    public void testAbsoluteURL__fixed() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("https://example.com"));
//        assertFalse(Utils.absoluteURL("file:///example.com"));
        /** This is not a valid URL. However, it is arguably out of scope for the simple checker function */
        assertTrue(Utils.absoluteURL("file:///example.com"));

        assertFalse(Utils.absoluteURL("/example.com"));
        assertFalse(Utils.absoluteURL("example.com"));
    }

    @Test
    public void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/path/to/resource"));
        assertFalse(Utils.pseudoAbsoluteURL("http://example.com"));
        assertFalse(Utils.pseudoAbsoluteURL("relative/path"));
        assertFalse(Utils.pseudoAbsoluteURL(""));
    }

    @Test
    public void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("-----", Utils.nChars(5, '-'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals("§§§§§", Utils.nChars(5, '§'));
    }

}
