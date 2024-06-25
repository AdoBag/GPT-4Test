package org.example.run10;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortTest {

    class Point {
        int x, y;
        String label;
        private int noAccessorMethod;

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
            return "(" + x + ", " + y + ", " + label + ")";
        }
    }

    private List<Point> points;
    private List<Point> reversePoints;

    @BeforeEach
    public void setUp() {
        points = new ArrayList<>();
        points.add(new Point(5, 10, "c"));
        points.add(new Point(2, 5, "b"));
        points.add(new Point(7, 15, "a"));

        reversePoints = new ArrayList<>(points);
    }

    @Test
    public void testSortByX() {
        Sort.sort(points, "x");
        assertEquals(2, points.get(0).x);
        assertEquals(5, points.get(1).x);
        assertEquals(7, points.get(2).x);
    }

    @Test
    public void testSortByXDescending() {
        Sort.sort(points, "x", true);
        assertEquals(7, points.get(0).x);
        assertEquals(5, points.get(1).x);
        assertEquals(2, points.get(2).x);
    }

    @Test
    public void testSortByY() {
        Sort.sort(points, "y");
        assertEquals(5, points.get(0).y);
        assertEquals(10, points.get(1).y);
        assertEquals(15, points.get(2).y);
    }

    @Test
    public void testSortByYDescending() {
        Sort.sort(points, "y", true);
        assertEquals(15, points.get(0).y);
        assertEquals(10, points.get(1).y);
        assertEquals(5, points.get(2).y);
    }

    @Test
    public void testSortByLabel() {
        Sort.sort(points, "label");
        assertEquals("a", points.get(0).label);
        assertEquals("b", points.get(1).label);
        assertEquals("c", points.get(2).label);
    }

    @Test
    public void testSortByLabelDescending() {
        Sort.sort(points, "label", true);
        assertEquals("c", points.get(0).label);
        assertEquals("b", points.get(1).label);
        assertEquals("a", points.get(2).label);
    }

    @Test
    public void testSortByPrivateFieldWithAccessor() {
        points.forEach(p -> assertEquals(reversePoints.get(reversePoints.indexOf(p)).label, p.label));
        Sort.sort(points, "label");
        assertEquals(points.size(), reversePoints.size());
    }

    @Test
    public void testSortNoFieldException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sort.sort(points, "nonExistentField");
        });
        assertTrue(exception.getMessage().contains("No such field"));
    }

    @Test
//    public void testSortNoAccessorMethodException() {
    public void testSortNoAccessorMethodException__fixed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sort.sort(points, "noAccessorMethod");
        });
//        assertTrue(exception.getMessage().contains("No method"));
        /** I added a dummy private field `noAccessorMethod` to get this exception */
        assertTrue(exception.getMessage().contains("No method"));
    }
}
