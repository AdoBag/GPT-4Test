
package org.example.run3;

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
        points.add(new Point(3, 2, "B"));
        points.add(new Point(1, 5, "A"));
        points.add(new Point(4, 1, "C"));
    }

    @Test
    void testSortByFieldAscending() {
        Sort.sort(points, "x");
        assertEquals(1, points.get(0).x);
        assertEquals(3, points.get(1).x);
        assertEquals(4, points.get(2).x);
    }

    @Test
    void testSortByFieldDescending() {
        Sort.sort(points, "y", true);
        assertEquals(5, points.get(0).y);
        assertEquals(2, points.get(1).y);
        assertEquals(1, points.get(2).y);
    }

    @Test
    void testSortByAccessorMethodAscending() {
        Sort.sort(points, "label");
        assertEquals("A", points.get(0).label);
        assertEquals("B", points.get(1).label);
        assertEquals("C", points.get(2).label);
    }

    @Test
    void testSortByAccessorMethodDescending() {
        List<PointWithPrivateLabel> pointsWithPrivateLabel = new ArrayList<>();
        pointsWithPrivateLabel.add(new PointWithPrivateLabel(3, 2, "B"));
        pointsWithPrivateLabel.add(new PointWithPrivateLabel(1, 5, "A"));
        pointsWithPrivateLabel.add(new PointWithPrivateLabel(4, 1, "C"));
        
        Sort.sort(pointsWithPrivateLabel, "label", true);
        
        assertEquals("C", pointsWithPrivateLabel.get(0).getLabel());
        assertEquals("B", pointsWithPrivateLabel.get(1).getLabel());
        assertEquals("A", pointsWithPrivateLabel.get(2).getLabel());
    }

    @Test
    void testNoSuchFieldException() {
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonexistent"));
    }

    @Test
    void testNoSuchMethodException() {
        List<PointWithPrivateFieldNoAccessor> pointsWithPrivateFieldNoAccessor = new ArrayList<>();
        pointsWithPrivateFieldNoAccessor.add(new PointWithPrivateFieldNoAccessor(3, 2, "B"));
        
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(pointsWithPrivateFieldNoAccessor, "label"));
    }
}

class Point {
    int x, y;
    String label;
    
    Point(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }
}

class PointWithPrivateLabel {
    public int x, y;
    private String label;
    
    PointWithPrivateLabel(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

class PointWithPrivateFieldNoAccessor {
    public int x, y;
    private String label;
    
    PointWithPrivateFieldNoAccessor(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }
}
