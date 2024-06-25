
package org.example.run3;

import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class StringComparatorTest {

    private final Comparator<String> comparator = new StringComparator();

    @Test
    public void testSameStrings() {
        String s1 = "hello";
        String s2 = "hello";
        assertEquals(0, comparator.compare(s1, s2));
    }

    @Test
    public void testDifferentStrings() {
        String s1 = "hello";
        String s2 = "world";
        assertTrue(comparator.compare(s1, s2) < 0);
    }

    @Test
    public void testAccentedCharacters() {
        String s1 = "C";
        String s2 = "Ç";
        assertTrue(comparator.compare(s1, s2) < 0);
    }

    @Test
    public void testUpperCaseLowerCaseEquality() {
        String s1 = "apple";
        String s2 = "Apple";
        assertEquals(0, comparator.compare(s1, s2));
    }

    @Test
    public void testCollatorLengthDifference() {
        String s1 = "hello";
        String s2 = "hell";
        assertTrue(comparator.compare(s1, s2) > 0);
    }

    @Test
    public void testEmptyStrings() {
        String s1 = "";
        String s2 = "";
        assertEquals(0, comparator.compare(s1, s2));
    }

//    @Test
//    public void testSpecialCharacters() {
//        String s1 = "hello";
//        String s2 = "hell@";
//        assertTrue(comparator.compare(s1, s2) < 0);
//    }
    /**
     * Manual fix of the above test
     * Problem: Java comparator considers `@` larger than `o`
     */
    @Test
    public void testSpecialCharacters__fixed() {
        String s1 = "hello";
        String s2 = "hell@";
        assertTrue(comparator.compare(s1, s2) > 0);
    }

    @Test
    public void testNullStrings() {
        String s1 = null;
        String s2 = "hello";
        assertThrows(NullPointerException.class, () -> comparator.compare(s1, s2));
    }
    
    @Test
//    public void testCompleteVoodooComparison() {
    public void testCompleteVoodooComparison__fixed() {
        String s1 = "C";
        String s2 = "D";
        assertTrue(comparator.compare(s1, s2) < 0);

//        s1 = "hello";
//        s2 = "hëllo";
//        assertEquals(0, comparator.compare(s1, s2));
        /** The purpose of the CUT is to distinguish between these strings */
        s1 = "hello";
        s2 = "hëllo";
        assertTrue(comparator.compare(s1, s2) < 0);

        s1 = "apple";
        s2 = "banana";
        assertTrue(comparator.compare(s1, s2) < 0);

        s1 = "Banana";
        s2 = "banana";
        assertEquals(0, comparator.compare(s1, s2));
    }
}
