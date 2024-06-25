
package org.example.run4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortTest {
    private List<Point> points;

    @BeforeEach
    void setUp() {
        points = new ArrayList<>();
        points.add(new Point(2, 3, "B"));
        points.add(new Point(1, 5, "A"));
        points.add(new Point(4, 2, "D"));
        points.add(new Point(3, 4, "C"));
    }

    @Test
    void testSortByFieldAscending() {
        Sort.sort(points, "x");
        assertEquals(1, points.get(0).x);
        assertEquals(2, points.get(1).x);
        assertEquals(3, points.get(2).x);
        assertEquals(4, points.get(3).x);
    }

    @Test
    void testSortByFieldDescending() {
        Sort.sort(points, "x", true);
        assertEquals(4, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(2, points.get(2).x);
        assertEquals(1, points.get(3).x);
    }

    @Test
    void testSortByPrivateFieldWithAccessorAscending() {
        Sort.sort(points, "label");
        assertEquals("A", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("C", points.get(2).getLabel());
        assertEquals("D", points.get(3).getLabel());
    }

    @Test
    void testSortByPrivateFieldWithAccessorDescending() {
        Sort.sort(points, "label", true);
        assertEquals("D", points.get(0).getLabel());
        assertEquals("C", points.get(1).getLabel());
        assertEquals("B", points.get(2).getLabel());
        assertEquals("A", points.get(3).getLabel());
    }

    @Test
    void testSortWithNonExistentField() {
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistentField"));
    }

    @Test
    void testSortWithNonExistentMethod() {
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonExistentMethod", true));
    }

    static class Point {
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
    }
}
