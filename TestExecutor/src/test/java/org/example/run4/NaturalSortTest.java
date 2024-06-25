
package org.example.run4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NaturalSortTest {

    @Test
    void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("file 10.txt", "file 100.txt"));
        assertEquals(1, NaturalSort.compare("file 100.txt", "file 10.txt"));
        assertEquals(0, NaturalSort.compare("file 100.txt", "file 100.txt"));
    }

    @Test
    void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("photo 7.jpeg", "PHOTO 7.jpeg"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("file 10.txt", "FILE 100.txt"));
        assertEquals(1, NaturalSort.compareIgnoreCase("FILE 100.txt", "file 10.txt"));
        assertEquals(0, NaturalSort.compareIgnoreCase("file 100.txt", "FILE 100.txt"));
    }

    @Test
    void testNaturalComparator() {
        List<String> myList = Arrays.asList("file 100.txt", "file 10.txt", "file 2.txt", "file 20.txt");
        Collections.sort(myList, new NaturalSort.NaturalComparator());
        assertEquals(List.of("file 2.txt", "file 10.txt", "file 20.txt", "file 100.txt"), myList);
    }

    @Test
    void testCINaturalComparator() {
        List<String> myList = Arrays.asList("FILE 100.txt", "file 10.txt", "File 2.txt", "fiLe 20.txt");
        Collections.sort(myList, new NaturalSort.CINaturalComparator());
        assertEquals(List.of("File 2.txt", "file 10.txt", "fiLe 20.txt", "FILE 100.txt"), myList);
    }
}
