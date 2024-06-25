```java
package org.example.run1;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue;
    private Queue limitedQueue;

    @Before
    public void setUp() {
        queue = new Queue();
        limitedQueue = new Queue(3); // Setting maximum capacity to 3 for testing
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue("Test");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        assertEquals(2, queue.getNumberItems());
    }

    @Test
    public void testDequeue() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        assertEquals("Object1", queue.dequeue());
        assertEquals(1, queue.getNumberItems());
        assertEquals("Object2", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemove() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        queue.enqueue("Object1");
        assertEquals(2, queue.remove("Object1"));
        assertEquals(1, queue.getNumberItems());
        assertEquals("Object2", queue.dequeue());
    }

    @Test
    public void testRefreshElement() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        queue.refreshElement("Object1");
        assertEquals("Object2", queue.dequeue());
        assertEquals("Object1", queue.dequeue());
    }

    @Test
    public void testGetNumberItems() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        assertEquals(2, queue.getNumberItems());
    }

    @Test
    public void testGetPeakNumberItems() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    public void testGetObjects() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        Vector objects = queue.getObjects();
        assertEquals(2, objects.size());
        assertTrue(objects.contains("Object1"));
        assertTrue(objects.contains("Object2"));
    }

    @Test
    public void testMaxCapacityExceeded() {
        limitedQueue.enqueue("Object1");
        limitedQueue.enqueue("Object2");
        limitedQueue.enqueue("Object3");
        assertTrue(limitedQueue.maxCapacityExceeded());
    }

    @Test
    public void testToString() {
        queue.enqueue("Object1");
        queue.enqueue("Object2");
        String expected = "org.example.run1.Queue:[numItems=2, maxNumItems=2, maxCapacity=-1, getObjects()=[Object1, Object2]\r\n]";
        assertEquals(expected, queue.toString());
    }
}
```