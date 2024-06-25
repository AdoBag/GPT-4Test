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
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(1);
        assertEquals(1, queue.getNumberItems());
        
        queue.enqueue(2);
        assertEquals(2, queue.getNumberItems());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.getNumberItems());
        
        assertEquals(2, queue.dequeue());
        assertEquals(0, queue.getNumberItems());
        
        queue.dequeue();  // Should print a message but not throw exception
    }

    @Test
    public void testRemove() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(3);

        int count = queue.remove(1);
        assertEquals(2, count);
        assertEquals(2, queue.getNumberItems());
    }

    @Test
    public void testRefreshElement() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.refreshElement(2);
        
        assertEquals(1, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(2, queue.dequeue());
    }

    @Test
    public void testGetNumberItems() {
        assertEquals(0, queue.getNumberItems());
        
        queue.enqueue(1);
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    public void testGetPeakNumberItems() {
        assertEquals(0, queue.getPeakNumberItems());
        
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.getPeakNumberItems());

        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    public void testGetObjects() {
        queue.enqueue(1);
        queue.enqueue(2);

        Vector objects = queue.getObjects();
        assertTrue(objects.contains(1));
        assertTrue(objects.contains(2));
        assertEquals(2, objects.size());
    }

    @Test
    public void testMaxCapacityExceeded() {
        Queue limitedQueue = new Queue(2);
        assertFalse(limitedQueue.maxCapacityExceeded());

        limitedQueue.enqueue(1);
        assertFalse(limitedQueue.maxCapacityExceeded());

        limitedQueue.enqueue(2);
        assertTrue(limitedQueue.maxCapacityExceeded());
    }

    @Test
    public void testToString() {
        queue.enqueue(1);
        queue.enqueue(2);
        String result = queue.toString();
        assertTrue(result.contains("numItems=2"));
        assertTrue(result.contains("maxNumItems=2"));
    }
}
```