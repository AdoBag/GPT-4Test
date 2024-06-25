
package org.example.run3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NaturalSortTest {
    
    @Test
    public void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 70.jpeg"));
    }
    
    @Test
    public void testCompareCaseInsensitive() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 17.jpeg", "photo 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 70.jpeg"));
    }

    @Test
    public void testNaturalComparator() {
        List<String> list = Arrays.asList("Photo 17.jpeg", "Photo 7.jpeg", "Photo 10.jpeg");
        Collections.sort(list, new NaturalSort.NaturalComparator());
        assertIterableEquals(Arrays.asList("Photo 7.jpeg", "Photo 10.jpeg", "Photo 17.jpeg"), list);
    }

    @Test
    public void testCINaturalComparator() {
        List<String> list = Arrays.asList("photo 17.jpeg", "Photo 7.jpeg", "PHOTO 10.jpeg");
        Collections.sort(list, new NaturalSort.CINaturalComparator());
        assertIterableEquals(Arrays.asList("Photo 7.jpeg", "PHOTO 10.jpeg", "photo 17.jpeg"), list);
    }

//    @Test
//    public void testCompareLeft() {
//        assertEquals(0, NaturalSort.compareLeft("100", 0, "100", 0));
//        assertEquals(-1, NaturalSort.compareLeft("123", 0, "124", 0));
//        assertEquals(1, NaturalSort.compareLeft("125", 0, "124", 0));
//    }
    /**
     * Manual fix of the above test
     * Problem: The method that the LLM tried to invoke was private
     */
    @Test
    public void testCompareLeft__fixed() {
        assertEquals(0, NaturalSort.compare("100", "100"));
        assertEquals(-1, NaturalSort.compare("123", "124"));
        assertEquals(1, NaturalSort.compare("125", "124"));
    }

//    @Test
//    public void testCompareRight() {
//        assertEquals(0, NaturalSort.compareRight("100", 0, "100", 0));
//        assertEquals(-1, NaturalSort.compareRight("123", 0, "124", 0));
//        assertEquals(1, NaturalSort.compareRight("125", 0, "124", 0));
//    }
    /**
     * Manual fix of the above test
     * Problem: The method that the LLM tried to invoke was private
     */
    @Test
    public void testCompareRight__fixed() {
        assertEquals(0, NaturalSort.compare("100", "100"));
        assertEquals(-1, NaturalSort.compare("123", "124"));
        assertEquals(1, NaturalSort.compare("125","124"));
    }
}
