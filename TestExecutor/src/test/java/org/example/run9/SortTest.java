
package org.example.run9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SortTest {
    
    private List<Point> points;

    @BeforeEach
    void setUp() {
        points = new ArrayList<>();
        points.add(new Point(3, 5, "C"));
        points.add(new Point(1, 2, "A"));
        points.add(new Point(2, 4, "B"));
    }

    @Test
    void testSortByFieldAscending() {
        Sort.sort(points, "x");
        assertEquals(1, points.get(0).x);
        assertEquals(2, points.get(1).x);
        assertEquals(3, points.get(2).x);
    }

    @Test
    void testSortByFieldDescending() {
        Sort.sort(points, "x", true);
        assertEquals(3, points.get(0).x);
        assertEquals(2, points.get(1).x);
        assertEquals(1, points.get(2).x);
    }

    @Test
    void testSortByMethodAscending() {
        Point pointWithAccessor = new Point(3, 5, "C");
        List<PointWithAccessor> pointsWithAccessor = new ArrayList<>();
        pointsWithAccessor.add(new PointWithAccessor(3, 5, "C"));
        pointsWithAccessor.add(new PointWithAccessor(1, 2, "A"));
        pointsWithAccessor.add(new PointWithAccessor(2, 4, "B"));

        Sort.sort(pointsWithAccessor, "label");
        assertEquals("A", pointsWithAccessor.get(0).getLabel());
        assertEquals("B", pointsWithAccessor.get(1).getLabel());
        assertEquals("C", pointsWithAccessor.get(2).getLabel());
    }

    @Test
    void testSortByMethodDescending() {
        List<PointWithAccessor> pointsWithAccessor = new ArrayList<>();
        pointsWithAccessor.add(new PointWithAccessor(3, 5, "C"));
        pointsWithAccessor.add(new PointWithAccessor(1, 2, "A"));
        pointsWithAccessor.add(new PointWithAccessor(2, 4, "B"));

        Sort.sort(pointsWithAccessor, "label", true);
        assertEquals("C", pointsWithAccessor.get(0).getLabel());
        assertEquals("B", pointsWithAccessor.get(1).getLabel());
        assertEquals("A", pointsWithAccessor.get(2).getLabel());
    }

    @Test
    void testInvalidFieldThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sort.sort(points, "nonExistentField");
        });
    }

    @Test
    void testInvalidMethodThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sort.sort(points, "nonExistentMethod");
        });
    }

    static class Point {
        int x, y;
        String label;

        Point(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }
    }

    static class PointWithAccessor {
        private int x, y;
        private String label;

        PointWithAccessor(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
