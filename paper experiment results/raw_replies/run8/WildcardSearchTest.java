```java
package org.example.run1;

import org.junit.Test;
import static org.junit.Assert.*;

public class WildcardSearchTest {

    @Test
    public void testEmptyTerm() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch("anything"));
    }

    @Test
    public void testEmptyValue() {
        WildcardSearch search = new WildcardSearch("something");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testTermAndValueBothEmpty() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testExactMatch() {
        WildcardSearch search = new WildcardSearch("test");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testWildcardAtEnd() {
        WildcardSearch search = new WildcardSearch("test*");
        assertTrue(search.doesMatch("testing"));
    }

    @Test
    public void testWildcardAtStart() {
        WildcardSearch search = new WildcardSearch("*test");
        assertTrue(search.doesMatch("mytest"));
    }

    @Test
    public void testMultipleWildcards() {
        WildcardSearch search = new WildcardSearch("t*e*s*t");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testWildcardInMiddle() {
        WildcardSearch search = new WildcardSearch("te*t");
        assertTrue(search.doesMatch("testing"));
    }

    @Test
    public void testNonMatchingCase() {
        WildcardSearch search = new WildcardSearch("test");
        assertFalse(search.doesMatch("testing123"));
    }

    @Test
    public void testWildcardMismatch() {
        WildcardSearch search = new WildcardSearch("t*s*");
        assertFalse(search.doesMatch("testing"));
    }

    @Test
    public void testWildcardBetweenCharacters() {
        WildcardSearch search = new WildcardSearch("te*st");
        assertTrue(search.doesMatch("teast"));
    }

    @Test
    public void testWildcardMatchesEmpty() {
        WildcardSearch search = new WildcardSearch("t*st");
        assertTrue(search.doesMatch("tst"));
    }

    @Test
    public void testWildcardWithExactMatch() {
        WildcardSearch search = new WildcardSearch("te*ting");
        assertFalse(search.doesMatch("testing123"));
    }
}
```