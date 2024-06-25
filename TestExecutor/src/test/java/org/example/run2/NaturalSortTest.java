
package org.example.run2;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NaturalSortTest {

    // Test for compare method (case-sensitive)
    @Test
    public void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 2.jpeg", "Photo 2.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
    }

    // Test for compareIgnoreCase method (case-insensitive)
    @Test
    public void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 2.jpeg", "photo 2.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 17.jpeg", "photo 7.jpeg"));
    }

    // Test for NaturalComparator (case-sensitive)
    @Test
    public void testNaturalComparator() {
        List<String> list = Arrays.asList("Photo 17.jpeg", "Photo 7.jpeg", "Photo 2.jpeg");
        list.sort(new NaturalSort.NaturalComparator());
        assertEquals(Arrays.asList("Photo 2.jpeg", "Photo 7.jpeg", "Photo 17.jpeg"), list);
    }

    // Test for CINaturalComparator (case-insensitive)
    @Test
    public void testCINaturalComparator() {
        List<String> list = Arrays.asList("Photo 17.jpeg", "photo 7.jpeg", "Photo 2.jpeg");
        list.sort(new NaturalSort.CINaturalComparator());
        assertEquals(Arrays.asList("Photo 2.jpeg", "photo 7.jpeg", "Photo 17.jpeg"), list);
    }

    // Test for compareRight method
    @Test
//    public void testCompareRight() {
    public void testCompareRight__fixed() {
        assertEquals(0, invokeCompareRight("50", 0, "50", 0));
        assertEquals(-1, invokeCompareRight("5", 0, "50", 0));
        assertEquals(1, invokeCompareRight("50", 0, "5", 0));
//        assertEquals(-1, invokeCompareRight("50", 2, "500", 3));
        /** Both indexes are out of bounds, thus, the strings are equal */
        assertEquals(0, invokeCompareRight("50", 2, "500", 3));
    }

    // Test for compareLeft method
    @Test
    public void testCompareLeft() {
        assertEquals(0, invokeCompareLeft("50", 0, "50", 0));
        assertEquals(-1, invokeCompareLeft("5", 0, "50", 0));
        assertEquals(1, invokeCompareLeft("50", 0, "5", 0));
        assertEquals(0, invokeCompareLeft("50", 2, "500", 3));
    }

    // Helper method to invoke private compareRight method
    private int invokeCompareRight(String a, int ai, String b, int bi) {
        try {
            var method = NaturalSort.class.getDeclaredMethod("compareRight", String.class, int.class, String.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(null, a, ai, b, bi);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to invoke private compareLeft method
    private int invokeCompareLeft(String a, int ai, String b, int bi) {
        try {
            var method = NaturalSort.class.getDeclaredMethod("compareLeft", String.class, int.class, String.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(null, a, ai, b, bi);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
