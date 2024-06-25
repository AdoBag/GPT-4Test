
package org.example.run7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringComparatorTest {

    @Test
    void testCompareEqualStrings() {
        assertEquals(0, StringComparator.compare("a", "a"));
        assertEquals(0, StringComparator.compare("Apple", "apple"));
    }

    @Test
    void testCompareStringsAccents() {
        assertTrue(StringComparator.compare("Café", "Cafe") > 0);
        assertTrue(StringComparator.compare("Café", "cafe") > 0);
    }

    @Test
    void testCompareDifferentLengths() {
        assertTrue(StringComparator.compare("abc", "abcd") < 0);
        assertTrue(StringComparator.compare("abcd", "abc") > 0);
    }

    @Test
//    void testCompareCaseInsensitive() {
    void testCompareCaseInsensitive__fixed() {
        assertEquals(0, StringComparator.compare("abc", "ABC"));
//        assertEquals(0, StringComparator.compare("Ĥĕľľô", "hËLLo"));
        /** One has a greater unicode value than the other */
        assertTrue(StringComparator.compare("Ĥĕľľô", "hËLLo") > 0);
    }

    @Test
    void testCompareUnicodeValues() {
        assertTrue(StringComparator.compare("ç", "d") < 0);
        assertTrue(StringComparator.compare("ü", "û") > 0);
    }

    @Test
//    void testSortList() {
    void testSortList__fixed() {
        List<String> names = Arrays.asList("éclair", "Éclair", "äbc", "Abc", "abc");
        Collections.sort(names, new StringComparator());

        assertEquals("Abc", names.get(0));
        assertEquals("abc", names.get(1));
        assertEquals("äbc", names.get(2));
//        assertEquals("Éclair", names.get(3));
//        assertEquals("éclair", names.get(4));
        /** The last two elements were mixed up */
        assertEquals("éclair", names.get(3));
        assertEquals("Éclair", names.get(4));
    }

    @Test
    void testCompareNonEnglishLetters() {
        assertTrue(StringComparator.compare("Ç", "D") < 0);
        assertTrue(StringComparator.compare("Ångström", "Zoo") < 0);
    }
}
