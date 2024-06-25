
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
//    void testEncodePath() {
    void testEncodePath__fixed() {
        assertEquals("home-user-docs", Utils.encodePath("home/user/docs"));
//        assertEquals("dollar-$$__-star_~", Utils.encodePath("dollar-$__-star*~"));
//        assertEquals("file_path--version~~", Utils.encodePath("file\\path-_version:~"));
        /** Incorrectly predicted output */
        assertEquals("dollar--$$____--star_~~", Utils.encodePath("dollar-$__-star*~"));
        assertEquals("file-path--__version~~~", Utils.encodePath("file\\path-_version:~"));
    }

//    @Test
//    void testEncodePathAsIdentifier() {
//        assertEquals("home_user_docs", Utils.encodePathAsIdentifier("home/user/docs"));
//        assertEquals("_36_dollar_45_45__36__36__45__36_star_42_45__126_", Utils.encodePathAsIdentifier("6dollar-$__star*~"));
//        assertEquals("java_lang_String", Utils.encodePathAsIdentifier("java.lang.String"));
//    }
    /**
     * Manual fix of the above test
     * Problem: Incorrectly predicted output
     */
    @Test
    void testEncodePathAsIdentifier__fixed() {
        assertEquals("home_47_user_47_docs", Utils.encodePathAsIdentifier("home/user/docs"));
        assertEquals("_54_dollar_45_$__star_42__126_", Utils.encodePathAsIdentifier("6dollar-$__star*~"));
        assertEquals("java_46_lang_46_String", Utils.encodePathAsIdentifier("java.lang.String"));
    }

    @Test
    void testAbsoluteURL() {
        assertTrue(Utils.absoluteURL("http://example.com"));
        assertTrue(Utils.absoluteURL("ftp://example.com"));
        assertFalse(Utils.absoluteURL("/relative/path"));
        assertFalse(Utils.absoluteURL("relative/path"));
    }

    @Test
    void testPseudoAbsoluteURL() {
        assertTrue(Utils.pseudoAbsoluteURL("/pseudo/absolute/url"));
        assertFalse(Utils.pseudoAbsoluteURL("relative/url"));
        assertFalse(Utils.pseudoAbsoluteURL("relative//absolute"));
    }

    @Test
//    void testNChars() {
    void testNChars__fixed() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("#####", Utils.nChars(5, '#'));
        assertEquals("", Utils.nChars(0, 'a'));
//        assertEquals(" bb", Utils.nChars(3, ' '));
        /** Incorrectly predicted output */
        assertEquals("   ", Utils.nChars(3, ' '));
    }
}
