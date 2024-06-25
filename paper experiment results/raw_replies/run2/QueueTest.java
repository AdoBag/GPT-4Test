```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testIsEmpty() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty(), "Queue should be empty after initialization");
        queue.enqueue("Test");
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    @Test
    void testEnqueue() {
        Queue queue = new Queue(2);
        queue.enqueue("Test1");
        assertEquals(1, queue.getNumberItems(), "Queue should contain one item");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems(), "Queue should contain two items");
    }

    @Test
    void testDequeue() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals("Test1", queue.dequeue(), "The first dequeued element should be 'Test1'");
        assertEquals("Test2", queue.dequeue(), "The second dequeued element should be 'Test2'");
        assertNull(queue.dequeue(), "Dequeueing from an empty queue should return null");
    }

    @Test
    void testRemove() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test1");
        assertEquals(2, queue.remove("Test1"), "Two elements should be removed");
        assertEquals(1, queue.getNumberItems(), "One element should remain in the queue");
    }

    @Test
    void testRefreshElement() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test3");
        queue.refreshElement("Test2");
        assertEquals("Test1", queue.dequeue(), "The first dequeued element should be 'Test1'");
        assertEquals("Test3", queue.dequeue(), "The second dequeued element should be 'Test3'");
        assertEquals("Test2", queue.dequeue(), "The third dequeued element should be 'Test2'");
    }

    @Test
    void testGetNumberItems() {
        Queue queue = new Queue();
        assertEquals(0, queue.getNumberItems(), "Queue should contain zero items after initialization");
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems(), "Queue should contain two items");
    }

    @Test
    void testGetPeakNumberItems() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems(), "Peak number of items in the queue should be 2");
    }

    @Test
    void testMaxCapacityExceeded() {
        Queue queue = new Queue(2);
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertTrue(queue.maxCapacityExceeded(), "Queue should exceed max capacity");
    }

    @Test
    void testGetObjects() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Vector expected = new Vector();
        expected.addElement("Test1");
        expected.addElement("Test2");
        assertEquals(expected, queue.getObjects(), "Queue should contain the same elements as the vector");
    }

    @Test
    void testToString() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        String expected = "org.example.run1.Queue:[numItems=2, maxNumItems=2, maxCapacity=-1, getObjects()=[Test1, Test2]\r\n]";
        assertEquals(expected, queue.toString(), "toString should return the correct representation of the queue");
    }
}
```