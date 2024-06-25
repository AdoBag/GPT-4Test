```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testExactMatch() {
        WildcardSearch ws = new WildcardSearch("example");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testSingleWildcard() {
        WildcardSearch ws = new WildcardSearch("ex*le");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testMultipleWildcards() {
        WildcardSearch ws = new WildcardSearch("ex*mp*le");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testLeadingWildcard() {
        WildcardSearch ws = new WildcardSearch("*ample");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testTrailingWildcard() {
        WildcardSearch ws = new WildcardSearch("exam*");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testOnlyWildcard() {
        WildcardSearch ws = new WildcardSearch("*");
        assertTrue(ws.doesMatch("example"));
    }

    @Test
    void testWildcardAtBeginningAndEnd() {
        WildcardSearch ws = new WildcardSearch("*exam*");
        assertTrue(ws.doesMatch("this is an example"));
    }

    @Test
    void testWildcardWithNoMatch() {
        WildcardSearch ws = new WildcardSearch("exa*ple");
        assertFalse(ws.doesMatch("exampler"));
    }

    @Test
    void testEmptySearchTerm() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch("example"));
    }

    @Test
    void testEmptyValue() {
        WildcardSearch ws = new WildcardSearch("exam*");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testBothEmpty() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testComplexPattern() {
        WildcardSearch ws = new WildcardSearch("e*am*le*");
        assertTrue(ws.doesMatch("extraterrestrial amphibian example"));
    }

    @Test
    void testNoWildcardNoMatch() {
        WildcardSearch ws = new WildcardSearch("sample");
        assertFalse(ws.doesMatch("example"));
    }

    @Test
    void testWildcardMiddleNoMatch() {
        WildcardSearch ws = new WildcardSearch("ex*xyz*le");
        assertFalse(ws.doesMatch("example"));
    }

    @Test
    void testMultipleWildcardsNoMatch() {
        WildcardSearch ws = new WildcardSearch("ex*mp*le*z");
        assertFalse(ws.doesMatch("example"));
    }
}
```