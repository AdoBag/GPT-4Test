
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class StringComparatorTest {

    @Test
    public void testEqualStrings() {
        assertEquals(0, StringComparator.compare("test", "test"));
    }

    @Test
    public void testDifferentStrings() {
        assertEquals(-1, StringComparator.compare("apple", "banana"));
        assertEquals(1, StringComparator.compare("banana", "apple"));
    }

    @Test
    public void testCaseInsensitiveComparison() {
        assertEquals(0, StringComparator.compare("abc", "ABC"));
    }

//    @Test
//    public void testAccentInsensitiveComparison() {
//        assertEquals(0, StringComparator.compare("é", "e"));
//        assertEquals(0, StringComparator.compare("ö", "o"));
//        assertEquals(0, StringComparator.compare("ü", "u"));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM misunderstood, non-latin characters are supposed to be greater than latin characters
     */
    @Test
    public void testAccentInsensitiveComparison__fixed() {
        assertTrue(StringComparator.compare("é", "e") > 0);
        assertTrue(StringComparator.compare("ö", "o") > 0);
        assertTrue(StringComparator.compare("ü", "u") > 0);
    }

    @Test
    public void testDifferentLengths() {
        assertEquals(-1, StringComparator.compare("apple", "applepie"));
        assertEquals(1, StringComparator.compare("applepie", "apple"));
    }

//    @Test
//    public void testSpecialCharacters() {
//        assertEquals(1, StringComparator.compare("apple", "Apple!"));
//        assertEquals(-1, StringComparator.compare("Apple!", "apple"));
//    }
    /**
     * Manual fix of the above test
     * Problem: Longer strings are considered as greater than shorter strings
     */
    @Test
    public void testSpecialCharacters__fixed() {
        assertEquals(-1, StringComparator.compare("apple", "Apple!"));
        assertEquals(1, StringComparator.compare("Apple!", "apple"));
    }

    @Test
    public void testSortArray() {
        String[] strings = {"banana", "Ápple", "apple", "Cherry", "ápplepie"};
        Arrays.sort(strings, new StringComparator());
        assertArrayEquals(new String[]{"apple", "Ápple", "ápplepie", "banana", "Cherry"}, strings);
    }

    @Test
    public void testNulls() {
        // Since original class doesn't handle nulls, we need to check and see if modifications are necessary later
        Exception exception = assertThrows(NullPointerException.class, () -> {
            StringComparator.compare(null, "apple");
        });
        assertEquals("Cannot invoke \"String.length()\" because \"s1\" is null", exception.getMessage());
    }
}
