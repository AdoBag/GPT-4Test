```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WildcardSearchTest {

    @Test
    public void testEmptySearchTermAndEmptyValue() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testEmptySearchTermAndNonEmptyValue() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch("value"));
    }

    @Test
    public void testNonEmptySearchTermAndEmptyValue() {
        WildcardSearch search = new WildcardSearch("term");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testExactMatch() {
        WildcardSearch search = new WildcardSearch("term");
        assertTrue(search.doesMatch("term"));
    }

    @Test
    public void testSingleWildcard() {
        WildcardSearch search = new WildcardSearch("t*rm");
        assertTrue(search.doesMatch("term"));
        assertTrue(search.doesMatch("tXrm"));
        assertFalse(search.doesMatch("trm"));
        assertFalse(search.doesMatch("termm"));
    }

    @Test
    public void testMultipleWildcards() {
        WildcardSearch search = new WildcardSearch("*e*t");
        assertTrue(search.doesMatch("test"));
        assertTrue(search.doesMatch("excellent"));
        assertTrue(search.doesMatch("et"));
        assertFalse(search.doesMatch("tst"));
    }

    @Test
    public void testTrailingWildcard() {
        WildcardSearch search = new WildcardSearch("ter*");
        assertTrue(search.doesMatch("term"));
        assertTrue(search.doesMatch("terminology"));
        assertFalse(search.doesMatch("tern"));
    }

    @Test
    public void testLeadingWildcard() {
        WildcardSearch search = new WildcardSearch("*erm");
        assertTrue(search.doesMatch("term"));
        assertTrue(search.doesMatch("hterm"));
        assertFalse(search.doesMatch("tern"));
    }

    @Test
    public void testConsecutiveWildcards() {
        WildcardSearch search = new WildcardSearch("t**rm");
        assertTrue(search.doesMatch("term"));
        assertTrue(search.doesMatch("tXrm"));
        assertFalse(search.doesMatch("trm"));
        assertFalse(search.doesMatch("termm"));
    }

    @Test
    public void testNoWildcardsButNoExactMatch() {
        WildcardSearch search = new WildcardSearch("term");
        assertFalse(search.doesMatch("trm"));
    }
}
```