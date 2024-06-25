
package org.example.run6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SortTest {

    class Point {
        public int x, y;
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
            return "Point(x=" + x + ", y=" + y + ", label=" + label + ")";
        }
    }

    @Test
    public void testSortByFieldAscending() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B"),
                new Point(1, 2, "A"),
                new Point(5, 0, "C")
        ));

        Sort.sort(points, "x");

        assertEquals(1, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(5, points.get(2).x);
    }

    @Test
    public void testSortByFieldDescending() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B"),
                new Point(1, 2, "A"),
                new Point(5, 0, "C")
        ));

        Sort.sort(points, "x", true);

        assertEquals(5, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(1, points.get(2).x);
    }

    @Test
    public void testSortByMethodAscending() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B"),
                new Point(1, 2, "A"),
                new Point(5, 0, "C")
        ));

        Sort.sort(points, "label");

        assertEquals("A", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("C", points.get(2).getLabel());
    }

    @Test
    public void testSortByMethodDescending() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B"),
                new Point(1, 2, "A"),
                new Point(5, 0, "C")
        ));

        Sort.sort(points, "label", true);

        assertEquals("C", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("A", points.get(2).getLabel());
    }

    @Test
    public void testSortEmptyList() {
        List<Point> points = new ArrayList<>();
        Sort.sort(points, "x");
        assertTrue(points.isEmpty());
    }

    @Test
    public void testSortNoSuchField() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B")
        ));

        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistentField"));
    }

    @Test
    public void testSortNoSuchMethod() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, "B")
        ));

        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistentMethod"));
    }

//    @Test
//    public void testNullValues() {
//        List<Point> points = new ArrayList<>(Arrays.asList(
//                new Point(3, 4, null),
//                new Point(1, 2, "A"),
//                new Point(5, 0, "C")
//        ));
//
//        Sort.sort(points, "label");
//
//        assertEquals("A", points.get(0).getLabel());
//        assertEquals("C", points.get(1).getLabel());
//        assertNull(points.get(2).getLabel());
//    }
    /**
     * Manual fix of the above test
     * Problem: The sort considers null as smaller than non-null values
     */
    @Test
    public void testNullValues__fixed() {
        List<Point> points = new ArrayList<>(Arrays.asList(
                new Point(3, 4, null),
                new Point(1, 2, "A"),
                new Point(5, 0, "C")
        ));
        Sort.sort(points, "label");
        assertNull(points.get(0).getLabel());
        assertEquals("A", points.get(1).getLabel());
        assertEquals("C", points.get(2).getLabel());
    }
}

