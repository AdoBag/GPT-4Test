```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class QueueTest {
    private Queue queue;
    private Queue boundedQueue;
    
    @BeforeEach
    public void setUp() {
        queue = new Queue();
        boundedQueue = new Queue(5);
    }
    
    @Test
    public void testQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("Test1");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getNumberItems());
    }
    
    @Test
    public void testDequeue() {
        queue.enqueue("Test1");
        assertEquals("Test1", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void testDequeueFromEmpty() {
        assertNull(queue.dequeue());
    }
    
    @Test
    public void testRemove() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test1");
        assertEquals(2, queue.remove("Test1"));
        assertEquals(1, queue.getNumberItems());
    }
    
    @Test
    public void testRefreshElement() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.refreshElement("Test1");
        
        assertTrue(queue.getObjects().lastElement().equals("Test1"));
    }
    
    @Test
    public void testGetNumberItems() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems());
    }
    
    @Test
    public void testGetPeakNumberItems() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }
    
    @Test
    public void testGetObjects() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Vector objects = queue.getObjects();
        assertEquals(2, objects.size());
        assertEquals("Test1", objects.elementAt(0));
        assertEquals("Test2", objects.elementAt(1));
    }
    
    @Test
    public void testMaxCapacityExceeded() {
        for (int i = 0; i < 5; i++) {
            boundedQueue.enqueue("Test" + i);
        }
        assertTrue(boundedQueue.maxCapacityExceeded());
        
        boundedQueue.dequeue();
        assertFalse(boundedQueue.maxCapacityExceeded());
    }
    
    @Test
    public void testToString() {
        queue.enqueue("Test1");
        String result = queue.toString();
        assertTrue(result.contains("numItems=1"));
        assertTrue(result.contains("getObjects()=[Test1]"));
    }
    
}
```