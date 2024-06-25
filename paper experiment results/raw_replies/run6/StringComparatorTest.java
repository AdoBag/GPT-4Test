```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringComparatorTest {
    
    @Test
    public void testCompareEqualStrings() {
        String s1 = "Test";
        String s2 = "Test";
        
        assertEquals(0, StringComparator.compare(s1, s2));
    }

    @Test
    public void testCompareDifferentStrings() {
        String s1 = "Test";
        String s2 = "test";
        
        assertEquals(0, StringComparator.compare(s1, s2));
    }

    @Test
    public void testCompareAccentStrings() {
        String s1 = "Café";
        String s2 = "Cafe";
        
        assertTrue(StringComparator.compare(s1, s2) > 0);
    }

    @Test
    public void testCompareNonEnglishLetters() {
        String s1 = "Ç";
        String s2 = "C";
        
        assertTrue(StringComparator.compare(s1, s2) > 0);
    }

    @Test
    public void testCompareStringLengths() {
        String s1 = "Test";
        String s2 = "Tests";
        
        assertTrue(StringComparator.compare(s1, s2) < 0);
    }

    @Test
    public void testCompareEmptyStrings() {
        String s1 = "";
        String s2 = "";
        
        assertEquals(0, StringComparator.compare(s1, s2));
    }

    @Test
    public void testCompareEmptyAndNonEmptyString() {
        String s1 = "";
        String s2 = "Test";
        
        assertTrue(StringComparator.compare(s1, s2) < 0);
    }

    @Test
    public void testSorting() {
        List<String> strings = Arrays.asList("Álvaro", "álvara", "John", "abacus", "çirkin", "Test", "test");
        
        Collections.sort(strings, new StringComparator());
        
        List<String> expected = Arrays.asList("abacus", "álvara", "Álvaro", "çirkin", "John", "Test", "test");
        
        assertEquals(expected, strings);
    }

    @Test
    public void testCompareCaseDifference() {
        String s1 = "Test";
        String s2 = "testTEST";
        
        assertTrue(StringComparator.compare(s1, s2) < 0);
    }

    @Test
    public void testCustomObjectComparator() {
        Object str1 = "Test";
        Object str2 = "test";
        
        StringComparator comparator = new StringComparator();
        
        assertEquals(0, comparator.compare(str1, str2));
    }
}
```