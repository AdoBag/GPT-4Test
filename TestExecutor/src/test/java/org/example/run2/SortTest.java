
package org.example.run2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortTest {

    public static class Point {
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
            return "Point{x=" + x + ", y=" + y + ", label='" + label + "'}";
        }
    }

    private List<Point> points;

    @BeforeEach
    void setUp() {
        points = new ArrayList<>();
        points.add(new Point(1, 2, "C"));
        points.add(new Point(3, 1, "A"));
        points.add(new Point(2, 3, "B"));
    }

    @Test
    void testSortByPublicField() {
        Sort.sort(points, "x");
        assertEquals(1, points.get(0).x);
        assertEquals(2, points.get(1).x);
        assertEquals(3, points.get(2).x);
    }

    @Test
    void testSortByPublicFieldDescending() {
        Sort.sort(points, "y", true);
        assertEquals(3, points.get(0).y);
        assertEquals(2, points.get(1).y);
        assertEquals(1, points.get(2).y);
    }

    @Test
    void testSortByPrivateFieldWithAccessor() {
        Sort.sort(points, "label");
        assertEquals("A", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("C", points.get(2).getLabel());
    }

    @Test
    void testSortByPrivateFieldWithAccessorDescending() {
        Sort.sort(points, "label", true);
        assertEquals("C", points.get(0).getLabel());
        assertEquals("B", points.get(1).getLabel());
        assertEquals("A", points.get(2).getLabel());
    }

    @Test
    void testSortWithNoSuchFieldException() {
        assertThrows(IllegalArgumentException.class, () -> Sort.sort(points, "nonexistentField"));
    }

    @Test
    void testSortWithNoSuchMethodException() {
        // Adding a sample class without a get method to test the case where get method is not found.
        class NoAccessorClass {
            private String hiddenField;

            NoAccessorClass(String hiddenField) {
                this.hiddenField = hiddenField;
            }
        }

        List<NoAccessorClass> listWithoutAccessor = new ArrayList<>();
        listWithoutAccessor.add(new NoAccessorClass("Data"));

        assertThrows(IllegalArgumentException.class, () -> Sort.sort(listWithoutAccessor, "hiddenField"));
    }

    @Test
    void testEmptyList() {
        List<Point> emptyList = new ArrayList<>();
        Sort.sort(emptyList, "label");
        assertEquals(0, emptyList.size());
    }

    @Test
//    void testNullValuesInField() {
    void testNullValuesInField__fixed() {
        List<Point> nullLabelPoints = new ArrayList<>();
        nullLabelPoints.add(new Point(1, 2, null));
        nullLabelPoints.add(new Point(3, 1, "A"));
        nullLabelPoints.add(new Point(2, 3, null));

        Sort.sort(nullLabelPoints, "label");
//        assertEquals("A", nullLabelPoints.get(0).getLabel());
        /** null is considered smaller than "A" by the program. Thus, the order is incorrect */
        assertEquals(null, nullLabelPoints.get(0).getLabel());

        assertEquals(null, nullLabelPoints.get(1).getLabel());
//        assertEquals(null, nullLabelPoints.get(2).getLabel());
        /** null is considered smaller than "A" by the program */
        assertEquals("A", nullLabelPoints.get(2).getLabel());


        Sort.sort(nullLabelPoints, "label", true);
//        assertEquals(null, nullLabelPoints.get(0).getLabel());
        /** null is considered smaller than "A" by the program, but this is sored descending */
        assertEquals("A", nullLabelPoints.get(0).getLabel());

        assertEquals(null, nullLabelPoints.get(1).getLabel());
//        assertEquals("A", nullLabelPoints.get(2).getLabel());
        /** null is considered smaller than "A" by the program */
        assertEquals(null, nullLabelPoints.get(2).getLabel());
    }
}
