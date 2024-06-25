
package org.example.run1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class NaturalSortTest {

    @Test
    public void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
    }

    @Test
    public void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 17.jpeg", "photo 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("photo 7.jpeg", "Photo 7.jpeg"));
    }

    @Test
    public void testNaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("Photo 17.jpeg");
        myList.add("Photo 7.jpeg");
        Collections.sort(myList, new NaturalSort.NaturalComparator());

        assertEquals("Photo 7.jpeg", myList.get(0));
        assertEquals("Photo 17.jpeg", myList.get(1));
    }

    @Test
    public void testCINaturalComparator() {
        List<String> myList = new ArrayList<>();
        myList.add("photo 17.jpeg");
        myList.add("Photo 7.jpeg");
        myList.add("photo 10.jpeg");
        Collections.sort(myList, new NaturalSort.CINaturalComparator());

        assertEquals("Photo 7.jpeg", myList.get(0));
        assertEquals("photo 10.jpeg", myList.get(1));
        assertEquals("photo 17.jpeg", myList.get(2));
    }

    @Test
    void testMixedContent() {
        assertTrue(NaturalSort.compare("file 10", "file 2") > 0);
        assertTrue(NaturalSort.compare("file 2.jpg", "file 10.jpg") < 0);
        assertTrue(NaturalSort.compare("file 10a", "file 10b") < 0);
        assertTrue(NaturalSort.compare("file 10", "file 10a") < 0);
    }

    @Test
    void testEqualStrings() {
        assertEquals(0, NaturalSort.compare("file 10", "file 10"));
        assertEquals(0, NaturalSort.compareIgnoreCase("file 10", "FILE 10"));
    }

    @Test
//    void testLeadingZeros() {
    void testLeadingZeros__fixed() {
//        assertEquals(0, NaturalSort.compare("file 0010", "file 10"));
        /** LLM incorrectly assumes that leading zeros are ignored, although in general it's a good assumption */
        assertEquals(-1, NaturalSort.compare("file 0010", "file 10"));

        assertTrue(NaturalSort.compare("file 0010", "file 00009") > 0);
    }

    @Test
    void testSpecialCharacters() {
        assertTrue(NaturalSort.compare("file-1", "file-10") < 0);
        assertTrue(NaturalSort.compare("file#10", "file$10") < 0);
    }
}
