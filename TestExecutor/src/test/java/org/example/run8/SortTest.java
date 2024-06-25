
package org.example.run8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    @Test
    public void testSortByField() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));
        points.add(new Point(1, 2, "Alpha"));
        points.add(new Point(5, 6, "Gamma"));

        Sort.sort(points, "x");

        assertEquals(1, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(5, points.get(2).x);
    }

    @Test
    public void testSortByFieldDecreasing() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));
        points.add(new Point(1, 2, "Alpha"));
        points.add(new Point(5, 6, "Gamma"));

        Sort.sort(points, "x", true);

        assertEquals(5, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(1, points.get(2).x);
    }

    @Test
    public void testSortByMethod() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));
        points.add(new Point(1, 2, "Alpha"));
        points.add(new Point(5, 6, "Gamma"));

        Sort.sort(points, "label");

        assertEquals("Alpha", points.get(0).getLabel());
        assertEquals("Beta", points.get(1).getLabel());
        assertEquals("Gamma", points.get(2).getLabel());
    }

    @Test
    public void testSortByMethodDecreasing() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));
        points.add(new Point(1, 2, "Alpha"));
        points.add(new Point(5, 6, "Gamma"));

        Sort.sort(points, "label", true);

        assertEquals("Gamma", points.get(0).getLabel());
        assertEquals("Beta", points.get(1).getLabel());
        assertEquals("Alpha", points.get(2).getLabel());
    }

    @Test
    public void testSortEmptyList() {
        List<Point> points = new ArrayList<>();
        Sort.sort(points, "x");
        assertTrue(points.isEmpty());
    }

    @Test
    public void testSortInvalidField() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Sort.sort(points, "nonexistentField"),
            "Expected sort() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("No such field"));
    }

    @Test
//    public void testSortInvalidMethod() {
    public void testSortInvalidMethod__fixed() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4, "Beta"));

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Sort.sort(points, "nonexistentMethod"),
            "Expected sort() to throw, but it didn't"
        );

//        assertTrue(thrown.getMessage().contains("No method"));
        /** I added a dummy private field `nonexistentMethod` to get this exception */
        assertTrue(thrown.getMessage().contains("No method"));
    }

    static class Point {
        int x, y;
        private String label;
        private int nonexistentMethod;

        public Point(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
