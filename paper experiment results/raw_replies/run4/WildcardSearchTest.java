```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testEmptyTermAndValue() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testEmptyTerm() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch("value"));
    }

    @Test
    void testEmptyValue() {
        WildcardSearch.ws = new WildcardSearch("term");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testExactMatch() {
        WildcardSearch ws = new WildcardSearch("test");
        assertTrue(ws.doesMatch("test"));
    }

    @Test
    void testSingleWildcardMatch() {
        WildcardSearch ws = new WildcardSearch("tes*");
        assertTrue(ws.doesMatch("test"));
        assertTrue(ws.doesMatch("testing"));
        assertFalse(ws.doesMatch("tes"));
    }

    @Test
    void testMultipleWildcards() {
        WildcardSearch ws = new WildcardSearch("*est*");
        assertTrue(ws.doesMatch("testing"));
        assertTrue(ws.doesMatch("test"));
        assertFalse(ws.doesMatch("tes"));
        assertTrue(ws.doesMatch("pretestingpost"));
    }

    @Test
    void testWildcardAtBeginning() {
        WildcardSearch ws = new WildcardSearch("*term");
        assertTrue(ws.doesMatch("preterm"));
        assertTrue(ws.doesMatch("term"));
        assertFalse(ws.doesMatch("ter"));
    }

    @Test
    void testWildcardAtEnding() {
        WildcardSearch ws = new WildcardSearch("term*");
        assertTrue(ws.doesMatch("term"));
        assertTrue(ws.doesMatch("termextra"));
        assertFalse(ws.doesMatch("ermextra"));
    }

    @Test
    void testIntermediateWildcard() {
        WildcardSearch ws = new WildcardSearch("te*st");
        assertTrue(ws.doesMatch("test"));
        assertTrue(ws.doesMatch("teeeest"));
        assertFalse(ws.doesMatch("testt"));
        assertFalse(ws.doesMatch("tteest"));
    }

    @Test
    void testNonMatchingWildcardPattern() {
        WildcardSearch ws = new WildcardSearch("te*st");
        assertFalse(ws.doesMatch("testing"));
        assertFalse(ws.doesMatch("tetest"));
    }

    @Test
    void testMultipleWildcardsNonMatching() {
        WildcardSearch ws = new WildcardSearch("*tes*ting*");
        assertFalse(ws.doesMatch("tetestingg"));
        assertFalse(ws.doesMatch("tesingting"));
        assertTrue(ws.doesMatch("atestbtestingc"));
    }

    @Test
    void testTrailingValuesAfterLastWildcard() {
        WildcardSearch ws = new WildcardSearch("te*st");
        assertTrue(ws.doesMatch("teeestst"));
        assertFalse(ws.doesMatch("teest"));
    }

    @Test
    void testValueEndingWithPattern() {
        WildcardSearch ws = new WildcardSearch("t*t");
        assertTrue(ws.doesMatch("testingt"));
        assertFalse(ws.doesMatch("testinger"));
    }

    @Test
    void testComplexPattern() {
        WildcardSearch ws = new WildcardSearch("t*s*g");
        assertTrue(ws.doesMatch("testing"));
        assertTrue(ws.doesMatch("timming"));
        assertFalse(ws.doesMatch("timmingn"));
    }
}
```

Ensure that you have the JUnit library added to your project dependencies so that the test cases can be executed properly.