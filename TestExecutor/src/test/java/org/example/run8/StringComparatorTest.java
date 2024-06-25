
package org.example.run8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class StringComparatorTest {
    
    private StringComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new StringComparator();
    }

    @Test
    public void testCompareEqualStrings() {
        assertEquals(0, comparator.compare("abc", "abc"));
        assertEquals(0, StringComparator.compare("abc", "abc"));
    }

    @Test
    public void testCompareDifferentStrings() {
        assertTrue(comparator.compare("abc", "abd") < 0);
        assertTrue(StringComparator.compare("abc", "abd") < 0);
        
        assertTrue(comparator.compare("abd", "abc") > 0);
        assertTrue(StringComparator.compare("abd", "abc") > 0);
    }

    @Test
    public void testCompareIgnoreCase() {
        assertEquals(0, comparator.compare("abc", "ABC"));
        assertEquals(0, StringComparator.compare("abc", "ABC"));

        assertTrue(comparator.compare("abc", "ABD") < 0);
        assertTrue(StringComparator.compare("abc", "ABD") < 0);

        assertTrue(comparator.compare("ABD", "abc") > 0);
        assertTrue(StringComparator.compare("ABD", "abc") > 0);
    }

    @Test
//    public void testCompareWithAccents() {
    public void testCompareWithAccents__fixed() {
//        assertTrue(comparator.compare("Café", "Cafe") < 0);
//        assertTrue(StringComparator.compare("Café", "Cafe") < 0);
        /** Incorrect order */
        assertTrue(comparator.compare("Café", "Cafe") > 0);
        assertTrue(StringComparator.compare("Café", "Cafe") > 0);

        assertTrue(comparator.compare("Café", "Déjà vu") < 0);
        assertTrue(StringComparator.compare("Café", "Déjà vu") < 0);
    }

    @Test
    public void testCompareWithUnicodeCharacters() {
        assertTrue(comparator.compare("Č", "C") > 0);
        assertTrue(StringComparator.compare("Č", "C") > 0);

        assertTrue(comparator.compare("C", "Č") < 0);
        assertTrue(StringComparator.compare("C", "Č") < 0);
    }

    @Test
    public void testCompareWithDifferentLengths() {
        assertTrue(comparator.compare("abc", "ab") > 0);
        assertTrue(StringComparator.compare("abc", "ab") > 0);

        assertTrue(comparator.compare("ab", "abc") < 0);
        assertTrue(StringComparator.compare("ab", "abc") < 0);
    }

    @Test
    public void testSortList() {
        List<String> list = new ArrayList<>();
        list.add("Café");
        list.add("banana");
        list.add("Apple");
        list.add("Déjà vu");

        Collections.sort(list, comparator);

        assertEquals("Apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("Café", list.get(2));
        assertEquals("Déjà vu", list.get(3));
    }

    @Test
    public void testSortListWithUnicode() {
        List<String> list = new ArrayList<>();
        list.add("Český");
        list.add("apple");
        list.add("Banana");
        list.add("Árbol");

        Collections.sort(list, comparator);

        assertEquals("apple", list.get(0));
        assertEquals("Árbol", list.get(1));
        assertEquals("Banana", list.get(2));
        assertEquals("Český", list.get(3));
    }
}
