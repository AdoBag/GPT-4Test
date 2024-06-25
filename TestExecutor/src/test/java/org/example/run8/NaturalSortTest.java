
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaturalSortTest {

    @Test
    public void testCompareNaturalOrder() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 10.jpeg", "Photo 10.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 20.jpeg", "Photo 3.jpeg"));
    }

    @Test
    public void testCompareWithLeadingZeros() {
        assertEquals(-1, NaturalSort.compare("Photo 007.jpeg", "Photo 017.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 010.jpeg", "Photo 010.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 020.jpeg", "Photo 003.jpeg"));
    }

    @Test
    public void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 10.jpeg", "photo 10.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("Photo 20.jpeg", "photo 3.jpeg"));
    }

    @Test
    public void testNaturalComparator() {
        List<String> images = new ArrayList<>();
        images.add("Photo 20.jpeg");
        images.add("Photo 3.jpeg");
        images.add("Photo 10.jpeg");
        Collections.sort(images, new NaturalSort.NaturalComparator());

        List<String> expected = new ArrayList<>();
        expected.add("Photo 3.jpeg");
        expected.add("Photo 10.jpeg");
        expected.add("Photo 20.jpeg");

        assertEquals(expected, images);
    }

    @Test
    public void testCINaturalComparator() {
        List<String> images = new ArrayList<>();
        images.add("Photo 20.jpeg");
        images.add("photo 3.jpeg");
        images.add("Photo 10.jpeg");
        Collections.sort(images, new NaturalSort.CINaturalComparator());

        List<String> expected = new ArrayList<>();
        expected.add("photo 3.jpeg");
        expected.add("Photo 10.jpeg");
        expected.add("Photo 20.jpeg");

        assertEquals(expected, images);
    }

    @Test
    public void testCompareWithMixedCharacters() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 7abc.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 7abc.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7abc.jpeg", "Photo 7abc.jpeg"));
    }
    
    @Test
    public void testCompareWithDifferentLengths() {
        assertEquals(1, NaturalSort.compare("Photo100.jpg", "Photo99.jpg"));
        assertEquals(-1, NaturalSort.compare("Photo2.jpg", "Photo10.jpg"));
        assertEquals(0, NaturalSort.compare("Photo10.jpg", "Photo10.jpg"));
    }

    @Test
    public void testCompareEmptyStrings() {
        assertEquals(0, NaturalSort.compare("", ""));
        assertEquals(-1, NaturalSort.compare("", "Photo 10.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 10.jpeg", ""));
    }
}
