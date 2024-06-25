```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WildcardSearchTest {

    @Test
    public void testDoesMatch_ExactMatch() {
        WildcardSearch search = new WildcardSearch("test");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_SingleWildcardMatch() {
        WildcardSearch search = new WildcardSearch("te*t");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_MultipleWildcardMatch() {
        WildcardSearch search = new WildcardSearch("te*st*ng*");
        assertTrue(search.doesMatch("testing123"));
    }

    @Test
    public void testDoesMatch_SingleCharacterNoMatch() {
        WildcardSearch search = new WildcardSearch("test");
        assertFalse(search.doesMatch("tast"));
    }

    @Test
    public void testDoesMatch_TrailingWildcard() {
        WildcardSearch search = new WildcardSearch("tes*");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_LeadingWildcard() {
        WildcardSearch search = new WildcardSearch("*est");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_EmptyTerm() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_EmptyValue() {
        WildcardSearch search = new WildcardSearch("test");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testDoesMatch_OnlyWildcardTerm() {
        WildcardSearch search = new WildcardSearch("*");
        assertTrue(search.doesMatch("anything"));
    }

    @Test
    public void testDoesMatch_MultipleAdjacentWildcards() {
        WildcardSearch search = new WildcardSearch("te**st");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_WildcardInMiddle() {
        WildcardSearch search = new WildcardSearch("te*ing");
        assertTrue(search.doesMatch("testing"));
    }

    @Test
    public void testDoesMatch_EmptyTermEmptyValue() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch(""));
    }

    @Test
    public void testDoesMatch_SingleCharacterWithWildcard() {
        WildcardSearch search = new WildcardSearch("t*");
        assertTrue(search.doesMatch("test"));
    }

    @Test
    public void testDoesMatch_MultipleNonMatchingCharacters() {
        WildcardSearch search = new WildcardSearch("t*s*");
        assertFalse(search.doesMatch("testing"));
    }
    
    @Test
    public void testDoesMatch_ComplexPattern() {
        WildcardSearch search = new WildcardSearch("*tes*ing*");
        assertTrue(search.doesMatch("123testing456"));
    }
}
```