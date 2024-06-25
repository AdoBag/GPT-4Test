```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class NaturalSortTest {

    @Test
    void testCompareNaturalOrder() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 10.jpeg"));
    }

    @Test
    void testCompareNaturalOrderCaseInsensitive() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 7.jpeg"));
    }

    @Test
    void testNaturalComparator() {
        List<String> list = Arrays.asList("Photo 17.jpeg", "Photo 7.jpeg", "Photo 10.jpeg");
        Collections.sort(list, new NaturalSort.NaturalComparator());
        assertEquals(Arrays.asList("Photo 7.jpeg", "Photo 10.jpeg", "Photo 17.jpeg"), list);
    }

    @Test
    void testCINaturalComparator() {
        List<String> list = Arrays.asList("photo 17.jpeg", "Photo 7.jpeg", "photo 10.jpeg");
        Collections.sort(list, new NaturalSort.CINaturalComparator());
        assertEquals(Arrays.asList("Photo 7.jpeg", "photo 10.jpeg", "photo 17.jpeg"), list);
    }

    @Test
    void testNumericalSorting() {
        assertEquals(0, NaturalSort.compare("123", "123"));
        assertEquals(-1, NaturalSort.compare("123", "124"));
        assertEquals(1, NaturalSort.compare("124", "123"));
    }

    @Test
    void testWhitespaceHandling() {
        assertEquals(0, NaturalSort.compare("file 1.txt", "file 1.txt"));
        assertEquals(-1, NaturalSort.compare("file 1.txt", "file 2.txt"));
        assertEquals(1, NaturalSort.compare("file 2.txt", "file 1.txt"));
    }

    @Test
    void testEmptyStrings() {
        assertEquals(0, NaturalSort.compare("", ""));
        assertEquals(-1, NaturalSort.compare("", "a"));
        assertEquals(1, NaturalSort.compare("a", ""));
    }

    @Test
    void testMixedContentSorting() {
        assertEquals(-1, NaturalSort.compare("file2.txt", "file10.txt"));
        assertEquals(1, NaturalSort.compare("file10.txt", "file2.txt"));
        assertEquals(0, NaturalSort.compare("file10.txt", "file10.txt"));
    }
}
```