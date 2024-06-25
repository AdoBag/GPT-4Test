
package org.example.run7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WildcardSearchTest {

    @Test
    public void testEmptySearchTerm() {
        WildcardSearch ws = new WildcardSearch("");
        assertFalse(ws.doesMatch("test"), "Empty search term should not match any value.");
    }

    @Test
    public void testEmptyValue() {
        WildcardSearch ws = new WildcardSearch("test");
        assertFalse(ws.doesMatch(""), "Non-empty search term should not match empty value.");
    }

    @Test
    public void testExactMatch() {
        WildcardSearch ws = new WildcardSearch("test");
        assertTrue(ws.doesMatch("test"), "Identical search term and value should match.");
    }

    @Test
    public void testSingleWildcardMatch() {
        WildcardSearch ws = new WildcardSearch("t*st");
        assertTrue(ws.doesMatch("test"), "Search term with single wildcard should match value.");
    }

    @Test
    public void testMultipleWildcardMatch() {
        WildcardSearch ws = new WildcardSearch("t*s*");
        assertTrue(ws.doesMatch("thisisatest"), "Search term with multiple wildcards should match value.");
    }

    @Test
    public void testWildcardAtStart() {
        WildcardSearch ws = new WildcardSearch("*test");
        assertTrue(ws.doesMatch("thisisatest"), "Search term with wildcard at start should match value ending with search term.");
    }

    @Test
    public void testWildcardAtEnd() {
        WildcardSearch ws = new WildcardSearch("test*");
        assertTrue(ws.doesMatch("testthisis"), "Search term with wildcard at end should match value starting with search term.");
    }

    @Test
    public void testNoMatch() {
        WildcardSearch ws = new WildcardSearch("test");
        assertFalse(ws.doesMatch("thisisatest"), "Search term with no wildcard should not match different value.");
    }

    @Test
    public void testComplexPatternMatch() {
        WildcardSearch ws = new WildcardSearch("t*h*s*s*t");
        assertTrue(ws.doesMatch("thisisatest"), "Complex search term should match value correctly.");
    }

    @Test
    public void testWildcardOnly() {
        WildcardSearch ws = new WildcardSearch("*");
        assertTrue(ws.doesMatch("anything"), "Search term with only wildcard should match any value.");
    }

    @Test
    public void testWildcardOnlyAtEnd() {
        WildcardSearch ws = new WildcardSearch("a*b*c*");
        assertTrue(ws.doesMatch("a1b2c"), "Search term with wildcards and no characters after last wildcard should match value correctly.");
    }
}
