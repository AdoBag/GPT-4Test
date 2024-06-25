```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testIsEmpty() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
        queue.enqueue("Test");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testEnqueueAndDequeue() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems());
        assertEquals("Test1", queue.dequeue());
        assertEquals(1, queue.getNumberItems());
        assertEquals("Test2", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testRemove() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test3");
        queue.enqueue("Test2");
        assertEquals(4, queue.getNumberItems());

        int removedCount = queue.remove("Test2");
        assertEquals(2, removedCount);
        assertEquals(2, queue.getNumberItems());
        assertEquals("Test1", queue.dequeue());
        assertEquals("Test3", queue.dequeue());
    }

    @Test
    void testRefreshElement() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems());

        queue.refreshElement("Test1");
        assertEquals(2, queue.getNumberItems());
        assertEquals("Test2", queue.dequeue());
        assertEquals("Test1", queue.dequeue());
    }

    @Test
    void testGetNumberItems() {
        Queue queue = new Queue();
        assertEquals(0, queue.getNumberItems());
        queue.enqueue("Test");
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    void testGetPeakNumberItems() {
        Queue queue = new Queue();
        assertEquals(0, queue.getPeakNumberItems());
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals(2, queue.getPeakNumberItems());
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    void testMaxCapacityExceeded() {
        Queue queue = new Queue(2);
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertFalse(queue.maxCapacityExceeded());
        queue.enqueue("Test3");
        assertTrue(queue.maxCapacityExceeded());
    }

    @Test
    void testGetObjects() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Vector objects = queue.getObjects();
        assertEquals(2, objects.size());
        assertEquals("Test1", objects.get(0));
        assertEquals("Test2", objects.get(1));
    }

    @Test
    void testToString() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        String expectedString = "org.example.run1.Queue:[numItems=2, maxNumItems=2, maxCapacity=-1, getObjects()=[Test1, Test2]\r\n]";
        assertEquals(expectedString, queue.toString());
    }
}
```