
package org.example.run10;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NaturalSortTest {

    @Test
    void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("photo 7.jpeg", "photo 7.jpeg"));  // Case sensitive
        assertEquals(-1, NaturalSort.compare("Photo 9.jpeg", "Photo 10.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 10.jpeg", "Photo 9.jpeg"));
    }

    @Test
    void testCompareIgnoreCase() {
        assertEquals(0, NaturalSort.compareIgnoreCase("photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("photo 7.jpeg", "PHOTO 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("PHOTO 7.jpeg", "photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("photo 9.jpeg", "photo 10.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("photo 10.jpeg", "photo 9.jpeg"));
    }

    @Test
    void testNaturalComparator() {
        List<String> list = new ArrayList<>();
        list.add("Photo 3.jpeg");
        list.add("Photo 2.jpeg");
        list.add("Photo 10.jpeg");
        list.add("Photo 1.jpeg");

        Collections.sort(list, new NaturalSort.NaturalComparator());
        
        assertEquals("Photo 1.jpeg", list.get(0));
        assertEquals("Photo 2.jpeg", list.get(1));
        assertEquals("Photo 3.jpeg", list.get(2));
        assertEquals("Photo 10.jpeg", list.get(3));
    }

    @Test
    void testCINaturalComparator() {
        List<String> list = new ArrayList<>();
        list.add("Photo 3.jpeg");
        list.add("photo 2.jpeg");
        list.add("photo 10.jpeg");
        list.add("PHOTO 1.jpeg");

        Collections.sort(list, new NaturalSort.CINaturalComparator());
        
        assertEquals("PHOTO 1.jpeg", list.get(0));
        assertEquals("photo 2.jpeg", list.get(1));
        assertEquals("Photo 3.jpeg", list.get(2));
        assertEquals("photo 10.jpeg", list.get(3));
    }
}
