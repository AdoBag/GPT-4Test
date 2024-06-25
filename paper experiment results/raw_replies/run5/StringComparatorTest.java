```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class StringComparatorTest {

    private final StringComparator comparator = new StringComparator();

    @Test
    public void testSameString() {
        assertEquals(0, comparator.compare("apple", "apple"));
    }

    @Test
    public void testDifferentStrings() {
        assertTrue(comparator.compare("apple", "banana") < 0);
        assertTrue(comparator.compare("banana", "apple") > 0);
    }

    @Test
    public void testCaseInsensitiveComparison() {
        assertEquals(0, comparator.compare("apple", "Apple"));
    }

    @Test
    public void testAccentInsensitiveComparison() {
        assertEquals(0, comparator.compare("Cafe", "Café"));
    }

    @Test
    public void testDifferentLengths() {
        assertTrue(comparator.compare("apple", "applepie") < 0);
        assertTrue(comparator.compare("applepie", "apple") > 0);
    }

    @Test
    public void testSortArray() {
        String[] fruits = {"banana", "apple", "Apple", "Café", "cafe", "banana", "Apple", "ßtraberry"};
        Arrays.sort(fruits, comparator);
        String[] expected = {"apple", "Apple", "Apple", "banana", "banana", "Cafe", "Café", "ßtraberry"};
        assertArrayEquals(expected, fruits);
    }

    @Test
    public void testSpecialCharacters() {
        assertTrue(comparator.compare("Cafe", "Café") == 0);
        assertTrue(comparator.compare("Café", "Cafe") == 0);
        assertTrue(comparator.compare("Æble", "Aeble") < 0);
    }

    @Test
    public void testEmptyStrings() {
        assertEquals(0, comparator.compare("", ""));
        assertTrue(comparator.compare("apple", "") > 0);
        assertTrue(comparator.compare("", "apple") < 0);
    }

    @Test
    public void testNumericStrings() {
        assertTrue(comparator.compare("123", "45") < 0);
        assertTrue(comparator.compare("9", "10") > 0);
        assertEquals(0, comparator.compare("007", "007"));
    }
}
```