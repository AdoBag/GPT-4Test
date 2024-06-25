```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildcardSearchTest {

    @Test
    void testDoesMatch_exactMatch() {
        WildcardSearch search = new WildcardSearch("hello");
        assertTrue(search.doesMatch("hello"));
    }

    @Test
    void testDoesMatch_noMatchDifferentLengths() {
        WildcardSearch search = new WildcardSearch("hello");
        assertFalse(search.doesMatch("helloo"));
        assertFalse(search.doesMatch("hell"));
    }

    @Test
    void testDoesMatch_singleWildcardBegin() {
        WildcardSearch search = new WildcardSearch("*world");
        assertTrue(search.doesMatch("helloworld"));
        assertFalse(search.doesMatch("worldhello"));
    }

    @Test
    void testDoesMatch_singleWildcardEnd() {
        WildcardSearch search = new WildcardSearch("hello*");
        assertTrue(search.doesMatch("helloworld"));
        assertFalse(search.doesMatch("hihello"));
    }

    @Test
    void testDoesMatch_wildcardInMiddle() {
        WildcardSearch search = new WildcardSearch("he*o");
        assertTrue(search.doesMatch("hello"));
        assertTrue(search.doesMatch("he1234o"));
        assertFalse(search.doesMatch("heo"));
    }

    @Test
    void testDoesMatch_multipleWildcards() {
        WildcardSearch search = new WildcardSearch("h*d*o");
        assertTrue(search.doesMatch("hellod12o"));
        assertFalse(search.doesMatch("hello12o"));
        assertTrue(search.doesMatch("hdodo"));
    }

    @Test
    void testDoesMatch_emptyTerm() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch("hello"));
    }

    @Test
    void testDoesMatch_emptyValue() {
        WildcardSearch search = new WildcardSearch("hello");
        assertFalse(search.doesMatch(""));
    }

    @Test
    void testDoesMatch_bothEmpty() {
        WildcardSearch search = new WildcardSearch("");
        assertFalse(search.doesMatch(""));
    }

    @Test
    void testDoesMatch_wildcardAtEnd() {
        WildcardSearch search = new WildcardSearch("hello*");
        assertTrue(search.doesMatch("hellotherethere"));
    }

    @Test
    void testDoesMatch_wildcardAtStartAndEnd() {
        WildcardSearch search = new WildcardSearch("*hello*");
        assertTrue(search.doesMatch("wellhellothere"));
        assertTrue(search.doesMatch("hellohello"));
        assertFalse(search.doesMatch("hellosir"));
    }

    @Test
    void testDoesMatch_noWildcardExactMatch() {
        WildcardSearch search = new WildcardSearch("world");
        assertFalse(search.doesMatch("hello"));
    }
}
```