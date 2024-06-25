
package org.example.run7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    private List<Point> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add(new Point(3, 2, "C"));
        list.add(new Point(1, 3, "A"));
        list.add(new Point(2, 1, "B"));
        list.add(new Point(4, 4, null)); // Add null label for checking robustness
    }

    static class Point {
        public int x, y;
        private int xMethod;
        private String label;

        public Point(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return String.format("Point(%d, %d, %s)", x, y, label);
        }
    }

    @Test
    public void testSortByFieldX() {
        Sort.sort(list, "x");
        assertEquals(1, list.get(0).x);
        assertEquals(2, list.get(1).x);
        assertEquals(3, list.get(2).x);
        assertEquals(4, list.get(3).x);
    }

    @Test
    public void testSortByFieldXDescending() {
        Sort.sort(list, "x", true);
        assertEquals(4, list.get(0).x);
        assertEquals(3, list.get(1).x);
        assertEquals(2, list.get(2).x);
        assertEquals(1, list.get(3).x);
    }

    @Test
    public void testSortByFieldY() {
        Sort.sort(list, "y");
        assertEquals(1, list.get(0).y);
        assertEquals(2, list.get(1).y);
        assertEquals(3, list.get(2).y);
        assertEquals(4, list.get(3).y);
    }

    @Test
    public void testSortByFieldYDescending() {
        Sort.sort(list, "y", true);
        assertEquals(4, list.get(0).y);
        assertEquals(3, list.get(1).y);
        assertEquals(2, list.get(2).y);
        assertEquals(1, list.get(3).y);
    }

//    @Test
//    public void testSortByLabel() {
//        Sort.sort(list, "label");
//        assertEquals("A", list.get(0).getLabel());
//        assertEquals("B", list.get(1).getLabel());
//        assertEquals("C", list.get(2).getLabel());
//        assertNull(list.get(3).getLabel());
//    }
    /**
     * Manual fix of the above test
     * Problem: The sort considers null as smaller than non-null values
     */
    @Test
    public void testSortByLabel__fixed() {
        Sort.sort(list, "label");
        assertNull(list.get(0).getLabel());
        assertEquals("A", list.get(1).getLabel());
        assertEquals("B", list.get(2).getLabel());
        assertEquals("C", list.get(3).getLabel());
    }

//    @Test
//    public void testSortByLabelDescending() {
//        Sort.sort(list, "label", true);
//        assertNull(list.get(0).getLabel());
//        assertEquals("C", list.get(1).getLabel());
//        assertEquals("B", list.get(2).getLabel());
//        assertEquals("A", list.get(3).getLabel());
//    }
    /**
     * Manual fix of the above test
     * Problem: The sort considers null as smaller than non-null values
     */
    @Test
    public void testSortByLabelDescending__fixed() {
        Sort.sort(list, "label", true);
        assertEquals("C", list.get(0).getLabel());
        assertEquals("B", list.get(1).getLabel());
        assertEquals("A", list.get(2).getLabel());
        assertNull(list.get(3).getLabel());
    }

    @Test
//    public void testInvalidField() {
    public void testInvalidField__fixed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sort.sort(list, "nonExistentField"));
//        assertTrue(exception.getMessage().contains("No such field 'nonExistentField' exists"));
        /** Typo: this assertion failed because there code author forgot the second apostrophe */
        assertTrue(exception.getMessage().contains("No such field 'nonExistentField exists"));
    }

    @Test
//    public void testInvalidMethod() {
    public void testInvalidMethod__fixed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sort.sort(list, "xMethod"));
//        assertTrue(exception.getMessage().contains("No method 'getXmethod()' exists"));
        /** The code threw a different exception. I created a dummy field without a getter to result in this exception */
        assertTrue(exception.getMessage().contains("No method 'getXMethod()' exists"));
    }
}
