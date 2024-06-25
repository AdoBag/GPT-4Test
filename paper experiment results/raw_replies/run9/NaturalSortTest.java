```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NaturalSortTest {

    @Test
    void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 17.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("file1.txt", "file10.txt"));
        assertEquals(0, NaturalSort.compare("file1.txt", "file1.txt"));
        assertEquals(-1, NaturalSort.compare("file1.txt", "file2.txt"));
    }

    @Test
    void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("photo 17.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 17.jpeg", "photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("FILE1.TXT", "file10.txt"));
        assertEquals(0, NaturalSort.compareIgnoreCase("FiLe1.txt", "file1.txt"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("file1.TXT", "file2.txt"));
    }

    @Test
    void testNaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("Photo 17.jpeg");
        myList.add("Photo 7.jpeg");
        myList.add("Photo 10.jpeg");
        myList.add("Photo 2.jpeg");

        Collections.sort(myList, new NaturalSort.NaturalComparator());
        assertEquals("Photo 2.jpeg", myList.get(0));
        assertEquals("Photo 7.jpeg", myList.get(1));
        assertEquals("Photo 10.jpeg", myList.get(2));
        assertEquals("Photo 17.jpeg", myList.get(3));
    }

    @Test
    void testCINaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("Photo 17.jpeg");
        myList.add("photo 7.jpeg");
        myList.add("Photo 10.jpeg");
        myList.add("photo 2.jpeg");

        Collections.sort(myList, new NaturalSort.CINaturalComparator());
        assertEquals("photo 2.jpeg", myList.get(0));
        assertEquals("photo 7.jpeg", myList.get(1));
        assertEquals("Photo 10.jpeg", myList.get(2));
        assertEquals("Photo 17.jpeg", myList.get(3));
    }
}
```