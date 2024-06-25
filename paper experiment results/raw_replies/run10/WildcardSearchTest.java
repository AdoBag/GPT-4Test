```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testEmptySearchTermAndValue() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testEmptySearchTerm() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch("abc"));
    }

    @Test
    void testEmptyValue_withNonEmptySearchTerm() {
        WildcardSearch ws = new WildcardSearch("abc");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testExactMatch() {
        WildcardSearch ws = new WildcardSearch("abc");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testMatchWithWildcardOnly() {
        WildcardSearch ws = new WildcardSearch("*");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testMatchWithLeadingWildcard() {
        WildcardSearch ws = new WildcardSearch("*bc");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testMatchWithTrailingWildcard() {
        WildcardSearch ws = new WildcardSearch("a*");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testMatchWithWildcardInMiddle() {
        WildcardSearch ws = new WildcardSearch("a*c");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testMatchWithMultipleWildcards() {
        WildcardSearch ws = new WildcardSearch("a*b*c");
        assertTrue(ws.doesMatch("axyzbxyzc"));
    }

    @Test
    void testMatchWithMultipleWildcardsAndSequentialSubterms() {
        WildcardSearch ws = new WildcardSearch("a*b*c*");
        assertTrue(ws.doesMatch("axyzbxyzc"));
    }

    @Test
    void testMismatchDueToExtraCharacters() {
        WildcardSearch ws = new WildcardSearch("a*b");
        assertFalse(ws.doesMatch("abxyz"));
    }

    @Test
    void testMismatchInExactMatch() {
        WildcardSearch ws = new WildcardSearch("abc");
        assertFalse(ws.doesMatch("abcd"));
    }

    @Test
    void testLeadingWildcardMatch() {
        WildcardSearch ws = new WildcardSearch("*cd");
        assertTrue(ws.doesMatch("abcd"));
    }

    @Test
    void testTrailingWildcardMatch() {
        WildcardSearch ws = new WildcardSearch("ab*");
        assertTrue(ws.doesMatch("abcd"));
    }

    @Test
    void testMatchWithWildcardInMiddleAndMismatch() {
        WildcardSearch ws = new WildcardSearch("a*c");
        assertFalse(ws.doesMatch("abcd"));
    }

    @Test
    void testWildcardAtStartAndEnd() {
        WildcardSearch ws = new WildcardSearch("*a*");
        assertTrue(ws.doesMatch("ba"));
    }
    
    @Test
    void testWildcardsAtStartAndEndWithPositions() {
        WildcardSearch ws = new WildcardSearch("*a*b*");
        assertTrue(ws.doesMatch("xaybz"));
    }

    @Test
    void testMatchWithAllWildcards() {
        WildcardSearch ws = new WildcardSearch("***");
        assertTrue(ws.doesMatch("abc"));
    }

    @Test
    void testNoMatchDueToCharacterMismatch() {
        WildcardSearch ws = new WildcardSearch("a*d");
        assertFalse(ws.doesMatch("abc"));
    }
}
```