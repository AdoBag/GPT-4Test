
package org.example.run4;

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

//    @Test
//    void testEmptyValue() {
//        WildcardSearch.ws = new WildcardSearch("term");
//        assertFalse(ws.doesMatch(""));
//    }
    /**
     * Manual fix of the above test
     * Problem: A syntax error
     */
    @Test
    void testEmptyValue__fixed() {
        WildcardSearch ws = new WildcardSearch("term");
        assertFalse(ws.doesMatch(""));
    }

    @Test
    void testExactMatch() {
        WildcardSearch ws = new WildcardSearch("test");
        assertTrue(ws.doesMatch("test"));
    }

    @Test
//    void testSingleWildcardMatch() {
    void testSingleWildcardMatch__fixed() {
        WildcardSearch ws = new WildcardSearch("tes*");
        assertTrue(ws.doesMatch("test"));
        assertTrue(ws.doesMatch("testing"));
//        assertFalse(ws.doesMatch("tes"));
        /** The given string matches the expression */
        assertTrue(ws.doesMatch("tes"));
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
//    void testNonMatchingWildcardPattern() {
    void testNonMatchingWildcardPattern__fixed() {
        WildcardSearch ws = new WildcardSearch("te*st");
        assertFalse(ws.doesMatch("testing"));
//        assertFalse(ws.doesMatch("tetest"));
        /** The given string matches the expression, so I changed it to a non-matching one */
        assertFalse(ws.doesMatch("ttetest"));
    }

    @Test
//    void testMultipleWildcardsNonMatching() {
    void testMultipleWildcardsNonMatching__fixed() {
        WildcardSearch ws = new WildcardSearch("*tes*ting*");
//        assertFalse(ws.doesMatch("tetestingg"));
//        assertFalse(ws.doesMatch("tesingting"));
        /** The given strings match the expression, so I changed them to non-matching ones */
        assertFalse(ws.doesMatch("tetetstingg"));
        assertFalse(ws.doesMatch("tesingtinng"));

        assertTrue(ws.doesMatch("atestbtestingc"));
    }

    @Test
//    void testTrailingValuesAfterLastWildcard() {
    void testTrailingValuesAfterLastWildcard__fixed() {
        WildcardSearch ws = new WildcardSearch("te*st");
        assertTrue(ws.doesMatch("teeestst"));
//        assertFalse(ws.doesMatch("teest"));
        /** The given string matches the expression, so I changed it to a non-matching one */
        assertFalse(ws.doesMatch("teests"));
    }

    @Test
    void testValueEndingWithPattern() {
        WildcardSearch ws = new WildcardSearch("t*t");
        assertTrue(ws.doesMatch("testingt"));
        assertFalse(ws.doesMatch("testinger"));
    }

    @Test
//    void testComplexPattern() {
    void testComplexPattern__fixed() {
        WildcardSearch ws = new WildcardSearch("t*s*g");
        assertTrue(ws.doesMatch("testing"));
//        assertTrue(ws.doesMatch("timming"));
        /** The given string did not match the expression, so I changed it to a matching one */
        assertTrue(ws.doesMatch("timminsg"));

        assertFalse(ws.doesMatch("timmingn"));
    }
}
