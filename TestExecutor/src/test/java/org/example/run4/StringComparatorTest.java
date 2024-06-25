
package org.example.run4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringComparatorTest {

    @Test
    public void testCompareSameStrings() {
        StringComparator comparator = new StringComparator();
        assertEquals(0, comparator.compare("apple", "apple"));
    }

    @Test
    public void testCompareDifferentCasedStrings() {
        StringComparator comparator = new StringComparator();
        assertEquals(0, comparator.compare("apple", "Apple"));
    }

//    @Test
//    public void testCompareStringsWithAccents() {
//        StringComparator comparator = new StringComparator();
//        assertEquals(0, comparator.compare("élève", "eleve"));
//        assertEquals(0, comparator.compare("café", "cafe"));
//    }
    /**
     * Manual fix of the above test
     * Problem: The purpose of this CUT is to distinguish between those strings
     */
    @Test
    public void testCompareStringsWithAccents__fixed() {
        StringComparator comparator = new StringComparator();
        assertTrue(comparator.compare("élève", "eleve") > 0);
        assertTrue(comparator.compare("café", "cafe") > 0);
    }

//    @Test
//    public void testCompareStringsDifferentAccents() {
//        StringComparator comparator = new StringComparator();
//        assertEquals(-1, comparator.compare("café", "cafè"));
//    }
    /**
     * Manual fix of the above test
     * Problem: One has a larger unicode value than the thoer
     */
    @Test
    public void testCompareStringsDifferentAccents__fixed() {
        StringComparator comparator = new StringComparator();
        assertEquals(1, comparator.compare("café", "cafè"));
    }

//    @Test
//    public void testCompareStringsWithSpecialCharacters() {
//        StringComparator comparator = new StringComparator();
//        assertEquals(0, comparator.compare("façade", "facade"));
//        assertEquals(-1, comparator.compare("facade", "façade"));
//    }
    /**
     * Manual fix of the above test
     * Problem: The purpose of this CUT is to distinguish between those strings
     */
    @Test
    public void testCompareStringsWithSpecialCharacters__fixed() {
        StringComparator comparator = new StringComparator();
        assertTrue(comparator.compare("façade", "facade") > 0);
        assertTrue(comparator.compare("facade", "façade") < 0);
    }

    @Test
    public void testCompareStringsDifferentLengths() {
        StringComparator comparator = new StringComparator();
        assertEquals(-1, comparator.compare("apple", "apple pie"));
        assertEquals(1, comparator.compare("apple pie", "apple"));
    }

    @Test
    public void testCompareStringsWithDifferentStartingCharacters() {
        StringComparator comparator = new StringComparator();
        assertEquals(-1, comparator.compare("apple", "banana"));
        assertEquals(1, comparator.compare("banana", "apple"));
    }

    @Test
    public void testCompareStringsWithNumbers() {
        StringComparator comparator = new StringComparator();
        assertEquals(0, comparator.compare("file1", "file1"));
        assertEquals(-1, comparator.compare("file1", "file2"));
    }
}
