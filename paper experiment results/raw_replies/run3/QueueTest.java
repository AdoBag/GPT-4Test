```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        queue.enqueue("TestElement");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("TestElement");
        assertEquals(1, queue.getNumberItems());
        assertEquals("TestElement", queue.dequeue());
    }

    @Test
    public void testDequeueEmpty() {
        assertNull(queue.dequeue());
    }

    @Test
    public void testDequeueSingleElement() {
        queue.enqueue("TestElement");
        assertEquals("TestElement", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueMultipleElements() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        assertEquals("Element1", queue.dequeue());
        assertEquals("Element2", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveNonExistentElement() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        assertEquals(0, queue.remove("NonExistentElement"));
        assertEquals(2, queue.getNumberItems());
        assertEquals("Element1", queue.dequeue());
    }

    @Test
    public void testRemoveExistingElement() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        queue.enqueue("Element3");
        assertEquals(1, queue.remove("Element2"));
        assertEquals(2, queue.getNumberItems());
        assertEquals("Element1", queue.dequeue());
        assertEquals("Element3", queue.dequeue());
    }

    @Test
    public void testRemoveAllOccurrences() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        queue.enqueue("Element1");
        assertEquals(2, queue.remove("Element1"));
        assertEquals(1, queue.getNumberItems());
        assertEquals("Element2", queue.dequeue());
    }

    @Test
    public void testRefreshElement() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        queue.refreshElement("Element1");
        assertEquals(2, queue.getNumberItems());
        assertEquals("Element2", queue.dequeue());
        assertEquals("Element1", queue.dequeue());
    }

    @Test
    public void testGetNumberItems() {
        assertEquals(0, queue.getNumberItems());
        queue.enqueue("TestElement");
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    public void testGetPeakNumberItems() {
        assertEquals(0, queue.getPeakNumberItems());
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    public void testGetObjects() {
        queue.enqueue("Element1");
        queue.enqueue("Element2");
        Vector objects = queue.getObjects();
        assertEquals(2, objects.size());
        assertEquals("Element1", objects.get(0));
        assertEquals("Element2", objects.get(1));
    }

    @Test
    public void testMaxCapacityExceededNoMaximum() {
        assertFalse(queue.maxCapacityExceeded());
        queue.enqueue("Element1");
        assertFalse(queue.maxCapacityExceeded());
    }

    @Test
    public void testMaxCapacityExceededWithCapacity() {
        queue = new Queue(1);
        assertFalse(queue.maxCapacityExceeded());
        queue.enqueue("Element1");
        assertFalse(queue.maxCapacityExceeded());
        queue.enqueue("Element2");
        assertTrue(queue.maxCapacityExceeded());
    }

    @Test
    public void testToString() {
        queue.enqueue("Element1");
        String expectedString = "org.example.run1.Queue:[numItems=1, maxNumItems=1, maxCapacity=-1, getObjects()=[Element1]\r\n]";
        assertEquals(expectedString, queue.toString());
    }
}
```