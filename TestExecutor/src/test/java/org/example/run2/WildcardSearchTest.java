
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testEmptyTerm() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch("test"));
    }

    @Test
    void testEmptyValue() {
        WildcardSearch search = new WildcardSearch("test");
        assertFalse(search.doesMatch(""));
    }

    @Test
    void testBothEmpty() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch(""));
    }
    
    @Test
    void testExactMatch() {
        WildcardSearch search = new WildcardSearch("test");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    void testSingleWildcard() {
        WildcardSearch search = new WildcardSearch("te*t");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    void testMultipleWildcards() {
        WildcardSearch search = new WildcardSearch("t*e*s*t");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    void testLeadingWildcard() {
        WildcardSearch search = new WildcardSearch("*test");
        assertTrue(search.doesMatch("mytest"));
    }

    @Test
    void testTrailingWildcard() {
        WildcardSearch search = new WildcardSearch("test*");
        assertTrue(search.doesMatch("testing"));
    }

    @Test
    void testMiddleWildcard() {
        WildcardSearch search = new WildcardSearch("te*t");
        assertTrue(search.doesMatch("te123t"));
    }

//    @Test
//    void testNoMatch() {
//        WildcardSearch search = new WildcardSearch("tes*");
//        assertFalse(search.doesMatch("test123"));
//    }
    /**
     * Manual fix of the above test
     * Problem: The given string matched the expression, so I changed the expression
     */
    @Test
    void testNoMatch__fixed() {
        WildcardSearch search = new WildcardSearch("ts*");
        assertFalse(search.doesMatch("test123"));
    }

    @Test
    void testWildcardInMiddleNoMatch() {
        WildcardSearch search = new WildcardSearch("t*s");
        assertFalse(search.doesMatch("test123"));
    }
    
    @Test
    void testSubtermNotFoundAfterWildcard() {
        WildcardSearch search = new WildcardSearch("t*st");
        assertFalse(search.doesMatch("t1234t"));
    }

    @Test
    void testWildcardAtEnd() {
        WildcardSearch search = new WildcardSearch("t*");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    void testWildcardAtBeginning() {
        WildcardSearch search = new WildcardSearch("*t");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    void testMultipleWildcardsNonConsecutiveSubterms() {
        WildcardSearch search = new WildcardSearch("t*a*t");
        assertTrue(search.doesMatch("teat"));
    }

    @Test
    void testMultipleWildcardsNonMatchingSubterms() {
        WildcardSearch search = new WildcardSearch("t*a*k");
        assertFalse(search.doesMatch("teat"));
    }
}
