
package org.example.run1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    static class Point {
        int x, y;
        private String label;

        Point(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }
        
        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Test
    void testSortByField() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(5, 2, "c"),
                new Point(1, 3, "b"),
                new Point(3, 1, "a")
        ));

        Sort.sort(points, "x");
        assertEquals(1, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(5, points.get(2).x);

        Sort.sort(points, "y", true);
        assertEquals(3, points.get(0).y);
        assertEquals(2, points.get(1).y);
        assertEquals(1, points.get(2).y);
    }

    @Test
    void testSortByMethod() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(5, 2, "c"),
                new Point(1, 3, "b"),
                new Point(3, 1, "a")
        ));

        Sort.sort(points, "label");
        assertEquals("a", points.get(0).getLabel());
        assertEquals("b", points.get(1).getLabel());
        assertEquals("c", points.get(2).getLabel());

        Sort.sort(points, "label", true);
        assertEquals("c", points.get(0).getLabel());
        assertEquals("b", points.get(1).getLabel());
        assertEquals("a", points.get(2).getLabel());
    }

    @Test
    void testSortNoField() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(5, 2, "c"),
                new Point(1, 3, "b"),
                new Point(3, 1, "a")
        ));
        
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "z"));
    }

    @Test
    void testSortNoMethod() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(5, 2, "c"),
                new Point(1, 3, "b"),
                new Point(3, 1, "a")
        ));

        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistentField"));
    }
    
    @Test
    void testEmptyList() {
        List<Point> points = new ArrayList<>();
        
        // Should not throw an error
        Sort.sort(points, "x");
        assertEquals(0, points.size());
    }
}
