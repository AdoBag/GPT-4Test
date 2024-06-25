```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testEmptySearchTerm() {
        WildcardSearch searcher = new WildcardSearch("");
        assertFalse(searcher.doesMatch("test"));
    }

    @Test
    void testEmptyValue() {
        WildcardSearch searcher = new WildcardSearch("test");
        assertFalse(searcher.doesMatch(""));
    }

    @Test
    void testExactMatch() {
        WildcardSearch searcher = new WildcardSearch("test");
        assertTrue(searcher.doesMatch("test"));
    }

    @Test
    void testSingleWildcardMatch() {
        WildcardSearch searcher = new WildcardSearch("te*t");
        assertTrue(searcher.doesMatch("test"));
    }

    @Test
    void testLeadingWildcardMatch() {
        WildcardSearch searcher = new WildcardSearch("*test");
        assertTrue(searcher.doesMatch("mytest"));
    }

    @Test
    void testTrailingWildcardMatch() {
        WildcardSearch searcher = new WildcardSearch("test*");
        assertTrue(searcher.doesMatch("testing"));
    }

    @Test
    void testMultipleWildcardsMatch() {
        WildcardSearch searcher = new WildcardSearch("t*s*");
        assertTrue(searcher.doesMatch("thisisatest"));
    }

    @Test
    void testMultipleWildcardsNoMatch() {
        WildcardSearch searcher = new WildcardSearch("t*s*t");
        assertFalse(searcher.doesMatch("thisisatest"));
    }

    @Test
    void testNoWildcardsNoMatch() {
        WildcardSearch searcher = new WildcardSearch("test");
        assertFalse(searcher.doesMatch("testcase"));
    }

    @Test
    void testComplexWildcardPatternMatch() {
        WildcardSearch searcher = new WildcardSearch("te*st*ca*e");
        assertTrue(searcher.doesMatch("teststringcase"));
    }

    @Test
    void testComplexWildcardPatternNoMatch() {
        WildcardSearch searcher = new WildcardSearch("te*st*ca*ex");
        assertFalse(searcher.doesMatch("teststringcase"));
    }
}
```