
package org.example.run10;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for the StringComparator class.
 */
public class StringComparatorTest {

    @Test
    public void testBasicComparison() {
        Comparator<String> comparator = new StringComparator();

        assertEquals(0, comparator.compare("abc", "abc"));
        assertTrue(comparator.compare("abc", "def") < 0);
        assertTrue(comparator.compare("def", "abc") > 0);
    }

    @Test
    public void testCaseInsensitiveComparison() {
        Comparator<String> comparator = new StringComparator();

        assertEquals(0, comparator.compare("abc", "ABC"));
        assertTrue(comparator.compare("abc", "DEF") < 0);
        assertTrue(comparator.compare("DEF", "abc") > 0);
    }

    @Test
//    public void testAccentInsensitiveComparison() {
    public void testAccentInsensitiveComparison__fixed() {
        Comparator<String> comparator = new StringComparator();

        assertEquals(0, comparator.compare("cafe", "CAFE"));
//        assertEquals(0, comparator.compare("café", "cafe"));
        /** The LLM misunderstood the comparison logic */
        assertTrue(comparator.compare("café", "cafe") > 0);

        assertTrue(comparator.compare("café", "CAFÉ") == 0);
    }

    @Test
    public void testMixedComparison() {
        Comparator<String> comparator = new StringComparator();

        assertTrue(comparator.compare("Cafe", "Café") < 0);
        assertTrue(comparator.compare("Café", "Cafe") > 0);

        assertTrue(comparator.compare("abc", "ÁBC") < 0);
        assertTrue(comparator.compare("ÁBC", "abc") > 0);
    }

//    @Test
//    public void testSorting() {
//        List<String> words = Arrays.asList(
//                "abcd", "ábcd", "ábce", "abce", "ABCD", "ÁBCD", "ÁBCE", "ABCe"
//        );
//        words.sort(new StringComparator());
//
//        List<String> expected = Arrays.asList(
//                "abcd", "ABCD", "ábcd", "ÁBCD", "abce", "ABCe", "ábce", "ÁBCE"
//        );
//
//        assertEquals(expected, words);
//    }
    /**
     * Manual fix of the above test
     * Problem: Incorrect order in the expected array
     */
    @Test
    public void testSorting__fixed() {
        List<String> words = Arrays.asList(
                "abcd", "ábcd", "ábce", "abce", "ABCD", "ÁBCD", "ÁBCE", "ABCe"
        );
        words.sort(new StringComparator());
        List<String> expected = Arrays.asList(
                "abcd", "ABCD", "abce", "ABCe", "ábcd", "ÁBCD", "ábce", "ÁBCE"
        );
        assertEquals(expected, words);
    }
}
