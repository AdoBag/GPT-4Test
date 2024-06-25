```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.Collator;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StringComparatorTest {

    private StringComparator stringComparator;

    @BeforeEach
    public void setUp() {
        stringComparator = new StringComparator();
    }

    @Test
    public void testCompareSameStrings() {
        assertEquals(0, StringComparator.compare("example", "example"));
    }

    @Test
    public void testCompareDifferentStringsSameLength() {
        assertTrue(StringComparator.compare("apple", "banana") < 0);
        assertTrue(StringComparator.compare("banana", "apple") > 0);
    }

    @Test
    public void testCompareDifferentStringsDifferentLength() {
        assertTrue(StringComparator.compare("apple", "apples") < 0);
        assertTrue(StringComparator.compare("apples", "apple") > 0);
    }

    @Test
    public void testCompareWithAccents() {
        assertTrue(StringComparator.compare("a", "á") < 0);
        assertTrue(StringComparator.compare("á", "a") > 0);
        assertTrue(StringComparator.compare("é", "e") > 0);
    }

    @Test
    public void testCompareCaseInsensitive() {
        assertEquals(0, StringComparator.compare("Apple", "apple"));
        assertEquals(0, StringComparator.compare("APPLE", "apple"));
        assertEquals(0, StringComparator.compare("aPPle", "ApPle"));
    }

    @Test
    public void testCompareLocaleAgnostic() {
        assertTrue(StringComparator.compare("Müller", "Muller") > 0);
        assertTrue(StringComparator.compare("Muller", "Müller") < 0);
    }

    @Test
    public void testCompareUsesCollator() {
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);
        assertEquals(0, collator.compare("Café", "CAFE"));
        assertEquals(0, StringComparator.compare("Café", "CAFE"));
    }

    @Test
    public void testCompareInListSorting() {
        String[] words = {"banana", "Apple", "apple", "Ápple", "Zebra", "müller"};
        Arrays.sort(words, stringComparator);
        
        assertArrayEquals(new String[]{"Apple", "apple", "Ápple", "banana", "müller", "Zebra"}, words);
    }
}
```