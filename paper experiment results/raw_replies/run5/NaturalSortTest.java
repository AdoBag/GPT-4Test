```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaturalSortTest {

    @Test
    public void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 007.jpeg", "Photo 7.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
    }

    @Test
    public void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 007.jpeg", "photo 7.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 17.jpeg", "photo 7.jpeg"));
    }

    @Test
    public void testNaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("Photo 17.jpeg");
        myList.add("Photo 7.jpeg");
        myList.add("Photo 10.jpeg");

        Collections.sort(myList, new NaturalSort.NaturalComparator());

        assertEquals("Photo 7.jpeg", myList.get(0));
        assertEquals("Photo 10.jpeg", myList.get(1));
        assertEquals("Photo 17.jpeg", myList.get(2));
    }

    @Test
    public void testCINaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("photo 17.jpeg");
        myList.add("PHOTO 7.jpeg");
        myList.add("Photo 10.jpeg");

        Collections.sort(myList, new NaturalSort.CINaturalComparator());

        assertEquals("PHOTO 7.jpeg", myList.get(0));
        assertEquals("Photo 10.jpeg", myList.get(1));
        assertEquals("photo 17.jpeg", myList.get(2));
    }

    @Test
    public void testCompareNumbersInString() {
        assertEquals(-1, NaturalSort.compare("file 9.txt", "file 10.txt"));
        assertEquals(1, NaturalSort.compare("file 12.txt", "file 10.txt"));
        assertEquals(0, NaturalSort.compare("file 01.txt", "file 1.txt"));
    }

    @Test
    public void testCompareStringsWithLeadingSpaces() {
        assertEquals(0, NaturalSort.compare("   Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("  Photo 7.jpeg", "Photo 8.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 8.jpeg", "  Photo 7.jpeg"));
    }

    @Test
    public void testCompareStringsWithDifferentLengths() {
        assertEquals(-1, NaturalSort.compare("a", "aa"));
        assertEquals(1, NaturalSort.compare("aa", "a"));
        assertEquals(-1, NaturalSort.compare("a", "ab"));
    }

    @Test
    public void testCompareEmptyStrings() {
        assertEquals(0, NaturalSort.compare("", ""));
        assertEquals(-1, NaturalSort.compare("", "a"));
        assertEquals(1, NaturalSort.compare("a", ""));
    }

    @Test
    public void testCompareStringsWithSpecialCharacters() {
        assertEquals(-1, NaturalSort.compare("a#1", "a#2"));
        assertEquals(1, NaturalSort.compare("a#2", "a#1"));
        assertEquals(0, NaturalSort.compare("a#1", "a#1"));
    }
}
```