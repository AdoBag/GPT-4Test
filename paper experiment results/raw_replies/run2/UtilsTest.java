```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testEncodePath() {
        assertEquals("home-user-docs", Utils.encodePath("home/user/docs"));
        assertEquals("dollar-$$__-star_~", Utils.encodePath("dollar-$__-star*~"));
        assertEquals("file_path--version~~", Utils.encodePath("file\\path-_version:~"));
    }

    @Test
    void testEncodePathAsIdentifier() {
        assertEquals("home_user_docs", Utils.encodePathAsIdentifier("home/user/docs"));
        assertEquals("_36_dollar_45_45__36__36__45__36_star_42_45__126_", Utils.encodePathAsIdentifier("6dollar-$__star*~"));
        assertEquals("java_lang_String", Utils.encodePathAsIdentifier("java.lang.String"));
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
    void testNChars() {
        assertEquals("aaaaa", Utils.nChars(5, 'a'));
        assertEquals("#####", Utils.nChars(5, '#'));
        assertEquals("", Utils.nChars(0, 'a'));
        assertEquals(" bb", Utils.nChars(3, ' '));
    }
}
```