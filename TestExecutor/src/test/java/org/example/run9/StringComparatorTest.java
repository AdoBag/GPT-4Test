
package org.example.run9;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringComparatorTest {

    @Test
    public void testEqualStrings() {
        // Test when both strings are the same
        assertEquals(0, StringComparator.compare("apple", "apple"));
    }

    @Test
    public void testDifferentStrings() {
        // Test when strings are different
        assertEquals(-1, StringComparator.compare("apple", "banana"));
        assertEquals(1, StringComparator.compare("banana", "apple"));
    }

//    @Test
//    public void testAccentStrings() {
//        // Test with accented characters
//        assertEquals(-1, StringComparator.compare("Café", "Cafe"));
//        assertEquals(1, StringComparator.compare("Cafe", "Café"));
//
//        // Test sorting of accentuated characters based on Unicode values
//        assertEquals(-1, StringComparator.compare("Café", "Càfe"));
//        assertEquals(1, StringComparator.compare("Càfe", "Café"));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM incorrectly understood the way the comparison logic
     */
    @Test
    public void testAccentStrings__fixed() {
        // Test with accented characters
        assertTrue(StringComparator.compare("Café", "Cafe") > 0);
        assertTrue(StringComparator.compare("Cafe", "Café") < 0);
        // Test sorting of accentuated characters based on Unicode values
        assertTrue(StringComparator.compare("Café", "Càfe") < 0);
        assertTrue(StringComparator.compare("Càfe", "Café") > 0);
    }

    @Test
    public void testCaseInsensitive() {
        // Test case insensitivity
        assertEquals(0, StringComparator.compare("apple", "Apple"));
        assertEquals(0, StringComparator.compare("banana", "BANANA"));
    }

    @Test
    public void testDifferentLengths() {
        // Test when one string is a prefix of another
        assertEquals(-1, StringComparator.compare("app", "apple"));
        assertEquals(1, StringComparator.compare("apple", "app"));
    }

    @Test
    public void testEmptyStrings() {
        // Test when one or both strings are empty
        assertEquals(0, StringComparator.compare("", ""));
        assertEquals(-1, StringComparator.compare("", "apple"));
        assertEquals(1, StringComparator.compare("apple", ""));
    }

    @Test
    public void testNumericStrings() {
        // Test with numeric strings
        assertEquals(-1, StringComparator.compare("123", "124"));
        assertEquals(1, StringComparator.compare("124", "123"));
        assertEquals(0, StringComparator.compare("123", "123"));
    }
}
