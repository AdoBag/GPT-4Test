
package org.example.run1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue("Item");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueueAndDequeue() {
        assertTrue(queue.isEmpty());

        Object firstItem = "First";
        Object secondItem = "Second";

        queue.enqueue(firstItem);
        queue.enqueue(secondItem);

        assertFalse(queue.isEmpty());
        assertEquals(firstItem, queue.dequeue());
        assertEquals(secondItem, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
//    public void testRemove() {
    public void testRemove__fixed() {
        Object firstItem = "First";
        Object secondItem = "Second";
//        Object thirdItem = "Second";
        /** Some of the assertions below were failing because the `thirdItem` was equal to `secondItem`. This is my
         * interpretation of what the LLM intended to achieve */
        Object thirdItem = "Third";

        queue.enqueue(firstItem);
        queue.enqueue(secondItem);
        queue.enqueue(thirdItem);

        assertEquals(1, queue.remove(firstItem));
        assertEquals(1, queue.remove(secondItem));

        assertFalse(queue.isEmpty());
        assertEquals(thirdItem, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRefreshElement() {
        Object firstItem = "First";
        Object secondItem = "Second";

        queue.enqueue(firstItem);
        queue.enqueue(secondItem);

        queue.refreshElement(firstItem);

        assertEquals(secondItem, queue.dequeue());
        assertEquals(firstItem, queue.dequeue());
    }

    @Test
    public void testGetNumberItems() {
        assertEquals(0, queue.getNumberItems());

        queue.enqueue("First");
        queue.enqueue("Second");

        assertEquals(2, queue.getNumberItems());
    }

    @Test
    public void testGetPeakNumberItems() {
        assertEquals(0, queue.getPeakNumberItems());

        queue.enqueue("First");
        queue.enqueue("Second");

        assertEquals(2, queue.getPeakNumberItems());

        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    public void testGetObjects() {
        Object firstItem = "First";
        Object secondItem = "Second";

        queue.enqueue(firstItem);
        queue.enqueue(secondItem);

        Vector expectedVector = new Vector();
        expectedVector.addElement(firstItem);
        expectedVector.addElement(secondItem);

        assertEquals(expectedVector, queue.getObjects());
    }

    @Test
    public void testMaxCapacityExceeded() {
        Queue capacityQueue = new Queue(2);

        capacityQueue.enqueue("First");
        assertFalse(capacityQueue.maxCapacityExceeded());

        capacityQueue.enqueue("Second");
        assertTrue(capacityQueue.maxCapacityExceeded());
    }

    @Test
    public void testToString() {
        queue.enqueue("First");
        queue.enqueue("Second");

        String expected = "org.example.run1.Queue:[numItems=2, maxNumItems=2, maxCapacity=-1, getObjects()=[First, Second]\r\n]";
        assertEquals(expected, queue.toString());
    }
}
