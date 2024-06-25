
package org.example.run5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    class Point {
        public int x;
        private int y;
        private String label;

        public Point(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    @Test
    void testSortByField() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));
        points.add(new Point(1, 4, "A"));
        points.add(new Point(2, 3, "C"));

        Sort.sort(points, "x");
        assertEquals("A", points.get(0).getLabel());
        assertEquals("C", points.get(1).getLabel());
        assertEquals("B", points.get(2).getLabel());
    }

    @Test
    void testSortByFieldDescending() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));
        points.add(new Point(1, 4, "A"));
        points.add(new Point(2, 3, "C"));

        Sort.sort(points, "x", true);
        assertEquals("B", points.get(0).getLabel());
        assertEquals("C", points.get(1).getLabel());
        assertEquals("A", points.get(2).getLabel());
    }

    @Test
    void testSortByMethod() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));
        points.add(new Point(1, 4, "A"));
        points.add(new Point(2, 3, "C"));

        Sort.sort(points, "label");
        assertEquals("A", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("C", points.get(2).getLabel());
    }

    @Test
    void testSortByMethodDescending() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));
        points.add(new Point(1, 4, "A"));
        points.add(new Point(2, 3, "C"));

        Sort.sort(points, "label", true);
        assertEquals("C", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("A", points.get(2).getLabel());
    }

    @Test
    void testSortThrowsNoSuchFieldException() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistingField"));
        assertEquals("No such field 'nonExistingField exists in class org.example.run5.SortTest$Point!", exception.getMessage());
    }

//    @Test
//    void testSortThrowsNoSuchMethodException() {
//        List<Point> points = new ArrayList<>();
//        points.add(new Point(3, 2, "B"));
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "y")); // y is not private but no accessor method
//        assertEquals("No method 'getY()' exists in class org.example.run5.SortTest$Point!", exception.getMessage());
//    }
    /**
     * Manual fix of the above test
     * Problem: Because `y` was public, it could be retrieved. To fix it, I made y private and ensured it did not
     * break other tests
     */
    @Test
    void testSortThrowsNoSuchMethodException__fixed() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2, "B"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "y"));
        assertEquals("No method 'getY()' exists in class org.example.run5.SortTest$Point!", exception.getMessage());
    }

}
